package app.tp136.dto;

import app.tp136.model.Payment;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDto {
    @NotBlank
    @Enumerated(EnumType.STRING)
    private Payment.PaymentStatus status;
    private BigDecimal amount;
    @NotBlank
    private boolean international;
    @Lob
    @NotBlank
    private String confirmation;
}
