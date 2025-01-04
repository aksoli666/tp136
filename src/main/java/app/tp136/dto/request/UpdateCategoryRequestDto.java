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
    private String name;
    @NotBlank
    private String description;

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
                .append(name, that.name)
                .append(description, that.description);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(name)
                .append(description);
        return hcb.toHashCode();
    }
}
