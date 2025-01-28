package app.tp136.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Setter
public class InspectionDto {
    @NotBlank
    private String description;
    @NotEmpty
    private Set<String> photos = new HashSet<>(5);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionDto)) {
            return false;
        }
        InspectionDto that = (InspectionDto) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(description, that.description)
                .append(photos, that.photos);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(description)
                .append(photos);
        return hcb.toHashCode();
    }
}
