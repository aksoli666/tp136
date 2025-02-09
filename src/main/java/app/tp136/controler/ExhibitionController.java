package app.tp136.controler;

import app.tp136.dto.ExhibitionDto;
import app.tp136.service.ExhibitionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Exhibition management", description = "Endpoints for managing exhibitions")
@RestController
@RequestMapping(value = "/exhibitions", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ExhibitionController {
    private final ExhibitionService exhibitionService;

    @Operation(
            summary = "Create a new exhibition",
            description = "Creates a new exhibition with the provided details."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MARKET_ADMIN', 'ROLE_COPYWRITER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ExhibitionDto createExhibition(@RequestBody @Valid ExhibitionDto exhibitionDto) {
        return exhibitionService.save(exhibitionDto);
    }

    @Operation(
            summary = "Get all exhibitions",
            description = "Retrieves a pageable list of all exhibitions."
    )
    @GetMapping
    public Page<ExhibitionDto> getAll(Pageable pageable) {
        return exhibitionService.getAll(pageable);
    }

    @Operation(
            summary = "Get all exhibitions by event name",
            description = "Fetches the details of a exhibition by its event name."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MARKET_ADMIN',"
            + " 'ROLE_COPYWRITER', 'ROLE_USER')")
    @GetMapping("/eventName")
    public Page<ExhibitionDto> getAllByEventName(@RequestParam @NotBlank String eventName,
                                                 Pageable pageable) {
        return exhibitionService.getExhibitionsByEvent(eventName, pageable);
    }

    @Operation(
            summary = "Get a exhibition by ID",
            description = "Fetches the details of a exhibition by its ID."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MARKET_ADMIN',"
            + " 'ROLE_COPYWRITER', 'ROLE_USER')")
    @GetMapping("/{id}")
    public ExhibitionDto getExhibition(@PathVariable @Positive Long id) {
        return exhibitionService.get(id);
    }

    @Operation(
            summary = "Update a exhibition by ID",
            description = "Updates the details of a specific exhibition."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MARKET_ADMIN', 'ROLE_COPYWRITER')")
    @PutMapping("/{id}")
    public ExhibitionDto updateExhibition(
            @PathVariable @Positive Long id,
            @RequestBody @Valid ExhibitionDto exhibitionDto) {
        return exhibitionService.update(id, exhibitionDto);
    }

    @Operation(
            summary = "Delete a exhibition by ID",
            description = "Deletes a exhibition by its ID."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MARKET_ADMIN', 'ROLE_COPYWRITER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteExhibition(@PathVariable @Positive Long id) {
        exhibitionService.delete(id);
    }
}
