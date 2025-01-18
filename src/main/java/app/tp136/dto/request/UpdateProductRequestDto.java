package app.tp136.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Setter
public class UpdateProductRequestDto {
    @NotBlank
    private String nameUa;
    @NotBlank
    private String nameEng;
    @NotBlank
    private String descriptionUa;
    @NotBlank
    private String descriptionEng;
    private String countryUa;
    private String countryEng;
    private int year;
    private String materialUa;
    private String materialEng;
    @NotNull
    @Positive
    private BigDecimal price;
    @NotNull
    private int inventory;
    @NotEmpty
    private Set<Long> categoryIds;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UpdateProductRequestDto)) {
            return false;
        }
        UpdateProductRequestDto that = (UpdateProductRequestDto) o;
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
                .append(inventory, that.inventory);
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
                .append(inventory);
        return hcb.toHashCode();
    }
}
