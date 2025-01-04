package app.tp136.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String name;
    private String description;
    private String country;
    private int year;
    private String material;
    private BigDecimal price;
    private int inventory;
    private Set<Long> categoryIds = new HashSet<>();
}
