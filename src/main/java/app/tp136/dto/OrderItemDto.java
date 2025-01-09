package app.tp136.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Setter
public class OrderItemDto {
    private Long id;
    private Long productId;
    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderItemDto)) {
            return false;
        }
        OrderItemDto that = (OrderItemDto) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(productId, that.productId)
                .append(quantity, that.quantity);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(productId)
                .append(quantity);
        return hcb.toHashCode();
    }
}
