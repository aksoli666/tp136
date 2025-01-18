package app.tp136.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Setter
public class UpdateCategoryRequestDto {
    @NotBlank
    private String nameUa;
    @NotBlank
    private String nameEng;
    @NotBlank
    private String descriptionUa;
    @NotBlank
    private String descriptionEng;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UpdateCategoryRequestDto)) {
            return false;
        }
        UpdateCategoryRequestDto that = (UpdateCategoryRequestDto) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(nameUa, that.nameUa)
                .append(nameEng, that.nameEng)
                .append(descriptionUa, that.descriptionUa)
                .append(descriptionEng, that.descriptionEng);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(nameUa)
                .append(nameEng)
                .append(descriptionUa)
                .append(descriptionEng);
        return hcb.toHashCode();
    }
}
