package app.tp136.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Setter
public class PlaceOrderRequestDto {
    @NotBlank
    private String shippingAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlaceOrderRequestDto)) {
            return false;
        }
        PlaceOrderRequestDto that = (PlaceOrderRequestDto) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(shippingAddress, that.shippingAddress);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(shippingAddress);
        return hcb.toHashCode();
    }
}
