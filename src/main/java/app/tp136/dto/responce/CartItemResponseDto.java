package app.tp136.dto.responce;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Setter
public class CartItemResponseDto {
    private Long productId;
    private String productName;
    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CartItemResponseDto)) {
            return false;
        }
        CartItemResponseDto that = (CartItemResponseDto) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(productName, that.productName)
                .append(quantity, that.quantity);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(productName)
                .append(quantity);
        return hcb.toHashCode();
    }
}
