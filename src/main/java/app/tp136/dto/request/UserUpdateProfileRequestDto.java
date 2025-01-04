package app.tp136.dto.request;

public record UserUpdateProfileRequestDto(
        String firstName,
        String lastName,
        String shippingAddress) {
}
