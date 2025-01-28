package app.tp136.controler;

import app.tp136.dto.TagDto;
import app.tp136.service.TagService;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Tag management", description = "Endpoints for managing tags")
@RestController
@RequestMapping(value = "/tags", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @Operation(
            summary = "Create a new tag",
            description = "Creates a new tag with the provided details."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MARKET_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TagDto createTag(@RequestBody @Valid TagDto dto) {
        return tagService.save(dto);
    }

    @Operation(
            summary = "Update a tag by ID",
            description = "Updates the details of a specific tag."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MARKET_ADMIN')")
    @PutMapping("/{id}")
    public TagDto updateTag(@PathVariable @Positive Long id,
                            @RequestBody @Valid TagDto dto) {
        return tagService.update(id, dto);
    }

    @Operation(
            summary = "Get a tag by ID",
            description = "Fetches the details of a tag by its ID."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MARKET_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    public TagDto getTag(@PathVariable @Positive Long id) {
        return tagService.get(id);
    }

    @Operation(
            summary = "Get all tags",
            description = "Retrieves a pageable list of all tags."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MARKET_ADMIN', 'ROLE_USER')")
    @GetMapping
    public Page<TagDto> getAll(Pageable pageable) {
        return tagService.getAll(pageable);
    }

    @Operation(
            summary = "Delete a tag by ID",
            description = "Deletes a tag by its ID."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MARKET_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable @Positive Long id) {
        tagService.delete(id);
    }
}
