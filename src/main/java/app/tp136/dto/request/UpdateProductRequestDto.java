package app.tp136.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    private String country;
    private int year;
    private String material;
    @NotNull
    @Positive
    private BigDecimal price;
    @NotNull
    private int inventory;
    @NotEmpty
    private Set<Long> categoryIds;
}
