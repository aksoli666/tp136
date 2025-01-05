package app.tp136.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
    @NotNull
    private LocalDate publicationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductWithoutCategoryDto)) {
            return false;
        }
        ProductWithoutCategoryDto that = (ProductWithoutCategoryDto) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(name, that.name)
                .append(description, that.description)
                .append(country, that.country)
                .append(year, that.year)
                .append(material, that.material)
                .append(price, that.price)
                .append(inventory, that.inventory)
                .append(publicationDate, that.publicationDate);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(name)
                .append(description)
                .append(country)
                .append(year)
                .append(material)
                .append(price)
                .append(inventory)
                .append(publicationDate);
        return hcb.toHashCode();
    }
}
