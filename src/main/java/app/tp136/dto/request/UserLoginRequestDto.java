package app.tp136.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Setter
public class UserLoginRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserLoginRequestDto)) {
            return false;
        }
        UserLoginRequestDto that = (UserLoginRequestDto) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(email, that.email)
                .append(password, that.password);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(email)
                .append(password);
        return hcb.toHashCode();
    }
}
