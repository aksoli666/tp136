package app.tp136.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Setter
public class CreateArticleRequestDto {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreateArticleRequestDto)) {
            return false;
        }
        CreateArticleRequestDto that = (CreateArticleRequestDto) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(titleUa, that.titleUa)
                .append(titleEng, that.titleEng)
                .append(authorUa, that.authorUa)
                .append(authorEng, that.authorEng)
                .append(contentUa, that.contentUa)
                .append(contentEng, that.contentEng);
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
                .append(contentEng);
        return hcb.toHashCode();
    }
}
