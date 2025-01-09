package app.tp136.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateProfileRequestDto(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String shippingAddress) {
}
