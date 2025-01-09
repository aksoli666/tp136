package app.tp136.dto.responce;

import app.tp136.dto.OrderItemDto;
import app.tp136.dto.StatusDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private LocalDateTime orderDate;
    private BigDecimal total;
    private StatusDto status;

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
                .append(orderDate, that.orderDate)
                .append(total, that.total);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(userId)
                .append(orderDate)
                .append(total);
        return hcb.toHashCode();
    }
}
