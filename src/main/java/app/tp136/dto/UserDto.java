package app.tp136.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Setter
public class UserDto {
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String auctionNumber;
    private boolean isVerified;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserDto)) {
            return false;
        }
        UserDto that = (UserDto) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(email, that.email)
                .append(phoneNumber, that.phoneNumber)
                .append(firstName, that.firstName)
                .append(lastName, that.lastName)
                .append(auctionNumber, that.auctionNumber);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(email)
                .append(phoneNumber)
                .append(firstName)
                .append(lastName)
                .append(auctionNumber);
        return hcb.toHashCode();
    }
}
