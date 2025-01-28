package app.tp136.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Setter
public class ArticleDto {
    @NotBlank
    private String titleUa;
    @NotBlank
    private String titleEng;
    @NotBlank
    private String contentUa;
    @NotBlank
    private String contentEng;
    @NotBlank
    private String authorUa;
    @NotBlank
    private String authorEng;
    private LocalDateTime published;
    @NotEmpty
    private Set<Long> tagIds = new HashSet<>();
    @NotEmpty
    private Set<String> photos = new HashSet<>(5);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArticleDto)) {
            return false;
        }
        ArticleDto that = (ArticleDto) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(titleUa, that.titleUa)
                .append(titleEng, that.titleEng)
                .append(authorUa, that.authorUa)
                .append(authorEng, that.authorEng)
                .append(contentUa, that.contentUa)
                .append(contentEng, that.contentEng)
                .append(published, that.published)
                .append(photos, that.photos);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(titleUa)
                .append(titleEng)
                .append(authorUa)
                .append(authorEng)
                .append(contentUa)
                .append(contentEng)
                .append(published)
                .append(photos);
        return hcb.toHashCode();
    }
}
