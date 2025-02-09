package app.tp136.dto;

import app.tp136.model.Exhibition;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Setter
public class ExhibitionDto {
    @NotBlank
    private String titleUa;
    @NotBlank
    private String titleEng;
    @NotBlank
    private String contentUa;
    @NotBlank
    private String contentEng;
    @NotNull
    private Exhibition.Event event;
    @NotBlank
    private String source;
    @NotBlank
    private String duration;
    @NotBlank
    private String location;
    @NotBlank
    private String address;
    @NotBlank
    private String entrance;
    @NotEmpty
    private Set<String> photos = new HashSet<>(5);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExhibitionDto)) {
            return false;
        }
        ExhibitionDto that = (ExhibitionDto) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(titleUa, that.titleUa)
                .append(titleEng, that.titleEng)
                .append(contentUa, that.contentUa)
                .append(contentEng, that.contentEng)
                .append(event, that.event)
                .append(photos, that.photos)
                .append(source, that.source);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(titleUa)
                .append(titleEng)
                .append(contentUa)
                .append(contentEng)
                .append(event)
                .append(photos)
                .append(source);
        return hcb.toHashCode();
    }
}
