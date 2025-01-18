package app.tp136.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Setter
public class PlaceOrderRequestDto {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String country;
    @NotBlank
    private String city;
    @NotBlank
    private String post;
    @NotBlank
    private String department;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlaceOrderRequestDto)) {
            return false;
        }
        PlaceOrderRequestDto that = (PlaceOrderRequestDto) o;
        EqualsBuilder eb = new EqualsBuilder()
                .append(firstName, that.firstName)
                .append(lastName, that.lastName)
                .append(country, that.country)
                .append(city, that.city)
                .append(post, that.post)
                .append(department, that.department);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(firstName)
                .append(lastName)
                .append(country)
                .append(city)
                .append(post)
                .append(department);
        return hcb.toHashCode();
    }
}
