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
    private String nameUa;
    private String nameEng;
    private String descriptionUa;
    private String descriptionEng;
    private String countryUa;
    private String countryEng;
    private int year;
    private String materialUa;
    private String materialEng;
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
                .append(nameUa, that.nameUa)
                .append(nameEng, that.nameEng)
                .append(descriptionUa, that.descriptionUa)
                .append(descriptionEng, that.descriptionEng)
                .append(countryUa, that.countryUa)
                .append(countryEng, that.countryEng)
                .append(year, that.year)
                .append(materialUa, that.materialUa)
                .append(materialEng, that.materialEng)
                .append(price, that.price)
                .append(inventory, that.inventory)
                .append(publicationDate, that.publicationDate);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(nameUa)
                .append(nameEng)
                .append(descriptionUa)
                .append(descriptionEng)
                .append(countryUa)
                .append(countryEng)
                .append(year)
                .append(materialUa)
                .append(materialEng)
                .append(price)
                .append(inventory)
                .append(publicationDate);
        return hcb.toHashCode();
    }
}
