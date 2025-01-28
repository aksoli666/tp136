package app.tp136.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Setter
public class CreateDiscussionRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotEmpty
    private Set<Long> topicIds;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreateDiscussionRequestDto)) {
            return false;
        }
        CreateDiscussionRequestDto that = (CreateDiscussionRequestDto) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(title, that.title)
                .append(content, that.content)
                .append(topicIds, that.topicIds);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(title)
                .append(content)
                .append(topicIds);
        return hcb.toHashCode();
    }
}
