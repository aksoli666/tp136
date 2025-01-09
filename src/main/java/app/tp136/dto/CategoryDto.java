package app.tp136.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryDto(
        @NotBlank Long id,
        @NotBlank String name,
        @NotBlank String description) {
}
