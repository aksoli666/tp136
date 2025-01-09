package app.tp136.dto.responce;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Setter
public class ShoppingCartResponseDto {
    private String userEmail;
    private Set<CartItemResponseDto> cartItems = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShoppingCartResponseDto)) {
            return false;
        }
        ShoppingCartResponseDto that = (ShoppingCartResponseDto) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(userEmail, that.userEmail);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(userEmail);
        return hcb.toHashCode();
    }
}
