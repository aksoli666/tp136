package app.tp136.dto.responce;

public record UserUpdateResponseDto(
        String email,
        String phoneNumber,
        String firstName,
        String lastName,
        String shippingAddress,
        String auctionNumber
) {
}
