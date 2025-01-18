package app.tp136.service;

import app.tp136.dto.PaymentDto;
import app.tp136.dto.request.CreateInternalPaymentRequestDto;
import app.tp136.dto.request.CreateInternationalPaymentRequestDto;
import app.tp136.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface PaymentService {
    PaymentDto get(Long id);

    Page<PaymentDto> getAll(Pageable pageable);

    void createInternalPayment(Authentication authentication,
                               CreateInternalPaymentRequestDto dto,
                               Long orderId);

    void createInternationalPayment(Authentication authentication,
                                    CreateInternationalPaymentRequestDto dto,
                                    Long orderId);

    void createCashPayment(Authentication authentication,
                           Long orderId);

    void approvePayment(Authentication authentication,
                        Long paymentId,
                        Payment.PaymentStatus status);
}
