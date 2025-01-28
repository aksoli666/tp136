package app.tp136.dto.responce;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InspectionResponseDto {
    @NotBlank
    private String description;
    @NotEmpty
    private Set<String> photos = new HashSet<>(5);
    @NotBlank
    private String userEmail;
}
