package app.tp136.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Setter
public class TagDto {
    @NotEmpty
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TagDto)) {
            return false;
        }
        TagDto that = (TagDto) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(name, that.name);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(name);
        return hcb.toHashCode();
    }
}
