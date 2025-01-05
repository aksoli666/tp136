package app.tp136.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
    private LocalDate publicationDate;
    private Set<Long> categoryIds = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductDto)) {
            return false;
        }
        ProductDto that = (ProductDto) o;
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
