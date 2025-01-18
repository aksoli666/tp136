package app.tp136.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryDto(
        @NotBlank Long id,
        @NotBlank String nameUa,
        @NotBlank String nameEng,
        @NotBlank String descriptionUa,
        @NotBlank String descriptionEng) {
}
