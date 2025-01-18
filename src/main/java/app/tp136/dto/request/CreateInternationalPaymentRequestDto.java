package app.tp136.dto.request;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Setter
public class CreateInternationalPaymentRequestDto {
    @NotBlank
    @Lob
    private String confirmation;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreateInternationalPaymentRequestDto)) {
            return false;
        }
        CreateInternationalPaymentRequestDto that =
                (CreateInternationalPaymentRequestDto) o;
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
