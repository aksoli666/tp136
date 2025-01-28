package app.tp136.controler;

import app.tp136.dto.InspectionDto;
import app.tp136.dto.responce.InspectionResponseDto;
import app.tp136.service.InspectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Inspection management", description = "Endpoints for managing inspections")
@RestController
@RequestMapping(value = "/inspections", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class InspectionController {
    private final InspectionService inspectionService;

    @Operation(
            summary = "Get a inspection by ID",
            description = "Fetches the details of a exhibition by its ID."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EXPERT', 'ROLE_USER')")
    @GetMapping("/{id}")
    public InspectionResponseDto getInspection(@PathVariable @Positive Long id) {
        return inspectionService.get(id);
    }

    @Operation(
            summary = "Get all inspections",
            description = "Retrieves a pageable list of all inspections."
    )
    @GetMapping
    public Page<InspectionResponseDto> getAll(Pageable pageable) {
        return inspectionService.getAll(pageable);
    }

    @Operation(
            summary = "Create a new basic assessment",
            description = "Creates a new assessment with the provided details."
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/basic-assessment")
    public void basicAssessment(Authentication authentication,
                                @RequestBody @Valid InspectionDto dto) {
        inspectionService.basicAssessment(authentication, dto);
    }

    @Operation(
            summary = "Create a new expertise",
            description = "Creates a new expertise with the provided details."
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/expertise")
    public void expertise(Authentication authentication,
                          @RequestBody @Valid InspectionDto dto) {
        inspectionService.expertise(authentication, dto);
    }
}
