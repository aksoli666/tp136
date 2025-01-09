package app.tp136.dto.request;

import jakarta.validation.constraints.Positive;

public record UpdateQuantityProductRequestDto(@Positive int quantity) {
}
