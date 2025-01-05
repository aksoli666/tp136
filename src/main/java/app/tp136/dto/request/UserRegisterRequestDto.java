package app.tp136.dto.request;

import app.tp136.validation.FieldMatch;
import app.tp136.validation.PhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@FieldMatch(
        firstFieldName = "password",
        secondFieldName = "repeatPassword",
        message = "Password must match!"
)
@Getter
@Setter
public class UserRegisterRequestDto {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @PhoneNumber
    private String phoneNumber;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String password;
    @NotBlank
    private String repeatPassword;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserRegisterRequestDto)) {
            return false;
        }
        UserRegisterRequestDto that = (UserRegisterRequestDto) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(email, that.email)
                .append(phoneNumber, that.phoneNumber)
                .append(firstName, that.firstName)
                .append(lastName, that.lastName)
                .append(password, that.password)
                .append(repeatPassword, that.repeatPassword);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(email)
                .append(phoneNumber)
                .append(firstName)
                .append(lastName)
                .append(password)
                .append(repeatPassword);
        return hcb.toHashCode();
    }
}
