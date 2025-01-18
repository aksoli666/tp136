package app.tp136.dto.request;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Setter
@Getter
public class CreateInternalPaymentRequestDto {
    @NotBlank
    @Lob
    private String confirmation;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreateInternalPaymentRequestDto)) {
            return false;
        }
        CreateInternalPaymentRequestDto that = (CreateInternalPaymentRequestDto) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(confirmation, that.confirmation);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(confirmation);
        return hcb.toHashCode();
    }
}
