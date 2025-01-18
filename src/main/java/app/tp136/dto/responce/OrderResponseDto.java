package app.tp136.dto.responce;

import app.tp136.dto.OrderItemDto;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private Long userId;
    private Set<OrderItemDto> orderItems = new HashSet<>();
    private BigDecimal total;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderResponseDto)) {
            return false;
        }
        OrderResponseDto that = (OrderResponseDto) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(userId, that.userId)
                .append(total, that.total);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(userId)
                .append(total);
        return hcb.toHashCode();
    }
}
