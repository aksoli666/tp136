package app.tp136.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductToCartRequestDto {
    @NotNull
    @Positive
    private Long productId;
    @Positive
    private int quantity;
}
