package app.tp136.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Setter
public class DiscussionDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String userFirstName;
    @NotBlank
    private String userLastName;
    @NotEmpty
    private Set<TopicDto> topics;
    @NotBlank
    private LocalDateTime published;
    @NotBlank
    private int countComments;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DiscussionDto)) {
            return false;
        }
        DiscussionDto that = (DiscussionDto) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(title, that.title)
                .append(content, that.content)
                .append(userFirstName, that.userFirstName)
                .append(userLastName, that.userLastName)
                .append(published, that.published)
                .append(countComments, that.countComments);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(title)
                .append(content)
                .append(userFirstName)
                .append(userLastName)
                .append(published)
                .append(countComments);
        return hcb.toHashCode();
    }
}
