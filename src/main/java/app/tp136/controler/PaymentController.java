package app.tp136.controler;

import app.tp136.dto.PaymentDto;
import app.tp136.dto.request.CreateInternalPaymentRequestDto;
import app.tp136.dto.request.CreateInternationalPaymentRequestDto;
import app.tp136.model.Payment;
import app.tp136.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Payment management", description = "Endpoints for managing payments")
@RestController
@RequestMapping(value = "/payments", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @Operation(
            summary = "Get a payment by ID",
            description = "Fetches the details of a payment by its ID."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MARKET_ADMIN')")
    @GetMapping("/{id}")
    public PaymentDto getPayment(@PathVariable @Positive Long id) {
        return paymentService.get(id);
    }

    @Operation(
            summary = "Get all payments",
            description = "Retrieves a pageable list of all payments."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MARKET_ADMIN', 'ROLE_USER')")
    @GetMapping
    public Page<PaymentDto> getAll(Pageable pageable) {
        return paymentService.getAll(pageable);
    }

    @Operation(
            summary = "Create a new internal payment",
            description = "Creates a new internal payment with the provided details."
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/internal/{orderId}")
    public ResponseEntity<String> createInternalPayment(
            Authentication authentication,
            @RequestBody @Valid CreateInternalPaymentRequestDto dto,
            @PathVariable @Valid Long orderId) {
        paymentService.createInternalPayment(authentication, dto, orderId);
        return ResponseEntity.ok("Internal payment created");
    }

    @Operation(
            summary = "Create a new international payment",
            description = "Creates a new international payment with the provided details."
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/international/{orderId}")
    public ResponseEntity<String> createInternationalPayment(
            Authentication authentication,
            @RequestBody @Valid CreateInternationalPaymentRequestDto dto,
            @PathVariable @Valid Long orderId) {
        paymentService.createInternationalPayment(authentication, dto, orderId);
        return ResponseEntity.ok("International payment created");
    }

    @Operation(
            summary = "Create a new cash payment",
            description = "Creates a new cash payment with the provided details."
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/cash/{orderId}")
    public ResponseEntity<String> createCashPayment(Authentication authentication,
                                                    @PathVariable @Valid Long orderId) {
        paymentService.createCashPayment(authentication, orderId);
        return ResponseEntity.ok("Cash payment created");
    }

    @Operation(
            summary = "Checks a payment",
            description = "Checks a payment."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MARKET_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/check/{paymentId}")
    public ResponseEntity<String> approvePayment(Authentication authentication,
                               @PathVariable @Positive Long paymentId,
                               @RequestParam Payment.PaymentStatus status) {
        paymentService.approvePayment(authentication, paymentId, status);
        return ResponseEntity.ok("Payment checked");
    }
}
