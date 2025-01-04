package app.tp136.dto.request;

import app.tp136.validation.FieldMatch;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@FieldMatch(
        firstFieldName = "newPassword",
        secondFieldName = "confirmPassword",
        message = "Password must match!"
)
@Getter
@Setter
public class UserResetPasswordRequestDto {
    @NotBlank
    private String newPassword;
    @NotBlank
    private String confirmPassword;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserResetPasswordRequestDto)) {
            return false;
        }
        UserResetPasswordRequestDto that = (UserResetPasswordRequestDto) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(newPassword, that.newPassword)
                .append(confirmPassword, that.confirmPassword);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(newPassword)
                .append(confirmPassword);
        return hcb.toHashCode();
    }
}
