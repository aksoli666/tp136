package app.tp136.dto;

import jakarta.validation.constraints.NotBlank;

public record StatusDto(@NotBlank String status) {
}
