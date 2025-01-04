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
public class UserUpdatePasswordRequestDto {
    @NotBlank
    private String oldPassword;
    @NotBlank
    private String newPassword;
    @NotBlank
    private String confirmPassword;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserUpdatePasswordRequestDto)) {
            return false;
        }
        UserUpdatePasswordRequestDto that = (UserUpdatePasswordRequestDto) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(oldPassword, that.oldPassword)
                .append(newPassword, that.newPassword)
                .append(confirmPassword, that.confirmPassword);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(oldPassword)
                .append(newPassword)
                .append(confirmPassword);
        return hcb.toHashCode();
    }
}
