package app.tp136.service.impl;

import app.tp136.dto.PaymentDto;
import app.tp136.dto.request.CreateInternalPaymentRequestDto;
import app.tp136.dto.request.CreateInternationalPaymentRequestDto;
import app.tp136.exception.ConfirmationException;
import app.tp136.exception.EntityNotFoundException;
import app.tp136.mapper.PaymentMapper;
import app.tp136.model.CashPayment;
import app.tp136.model.Order;
import app.tp136.model.Payment;
import app.tp136.model.TransferPayment;
import app.tp136.repository.OrderRepository;
import app.tp136.repository.PaymentRepository;
import app.tp136.security.CustomUserDetailsService;
import app.tp136.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private static final boolean INTERNAL = false;
    private static final boolean INTERNATIONAL = true;

    private final CustomUserDetailsService userDetailsService;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentDto get(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can`t find payment. Id: " + id));
        return paymentMapper.toDto(payment);
    }

    @Override
    public Page<PaymentDto> getAll(Pageable pageable) {
        return paymentMapper.toDtoPage(paymentRepository.findAll(pageable));
    }

    @Override
    public void createInternalPayment(Authentication authentication,
                                      CreateInternalPaymentRequestDto dto,
                                      Long orderId) {
        Long userId = userDetailsService.getUserIdFromAuthentication(authentication);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Can`t find order. Id: " + orderId));
        TransferPayment transferPayment = initializeTransferPayment(
                order, INTERNAL, dto.getConfirmation());
        paymentRepository.save(transferPayment);
    }

    @Override
    public void createInternationalPayment(Authentication authentication,
                                           CreateInternationalPaymentRequestDto dto,
                                           Long orderId) {
        Long userId = userDetailsService.getUserIdFromAuthentication(authentication);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Can`t find order. Id: " + orderId));
        TransferPayment transferPayment = initializeTransferPayment(
                order, INTERNATIONAL, dto.getConfirmation());
        paymentRepository.save(transferPayment);
    }

    @Override
    public void createCashPayment(Authentication authentication, Long orderId) {
        Long userId = userDetailsService.getUserIdFromAuthentication(authentication);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Can`t find order. Id: " + orderId));
        CashPayment cashPayment = initializeCashPayment(order);
        paymentRepository.save(cashPayment);
    }

    @Override
    public void approvePayment(Authentication authentication,
                               Long paymentId,
                               Payment.PaymentStatus status) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find payment by id. Id " + paymentId));

        if (status == Payment.PaymentStatus.PROCESSING) {
            validateConfirmation(payment);
        }

        payment.setStatus(status);
        paymentRepository.save(payment);
    }

    private TransferPayment initializeTransferPayment(Order order,
                                                      boolean isInternational,
                                                      String confirmation) {
        TransferPayment transferPayment = new TransferPayment();
        transferPayment.setInternational(isInternational);
        transferPayment.setStatus(Payment.PaymentStatus.PROCESSING);
        transferPayment.setAmount(order.getTotal());
        transferPayment.setConfirmation(confirmation);
        return transferPayment;
    }

    private CashPayment initializeCashPayment(Order order) {
        CashPayment cashPayment = new CashPayment();
        cashPayment.setAmount(order.getTotal());
        cashPayment.setStatus(Payment.PaymentStatus.PROCESSING);
        return cashPayment;
    }

    private void validateConfirmation(Payment payment) {
        if (payment instanceof TransferPayment transferPayment) {
            if (transferPayment.getConfirmation() == null
                    || transferPayment.getConfirmation().isEmpty()) {
                throw new ConfirmationException(
                        "Confirmation screenshot is required for transfer payments.");
            }
        }
    }
}
