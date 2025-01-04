package app.tp136.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductWithoutCategoryDto {
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
}
