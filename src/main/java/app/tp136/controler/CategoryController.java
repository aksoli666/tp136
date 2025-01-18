package app.tp136.controler;

import app.tp136.dto.CategoryDto;
import app.tp136.dto.request.CreateCategoryRequestDto;
import app.tp136.dto.request.UpdateCategoryRequestDto;
import app.tp136.service.CategoryService;
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

@Tag(name = "Category management", description = "Endpoints for managing categories")
@RestController
@RequestMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(
            summary = "Create a new category",
            description = "Creates a new category with the provided details."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MARKET_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryDto createCategory(@RequestBody @Valid CreateCategoryRequestDto requestDto) {
        return categoryService.save(requestDto);
    }

    @Operation(
            summary = "Get all categories",
            description = "Retrieves a pageable list of all categories."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MARKET_ADMIN', 'ROLE_USER')")
    @GetMapping
    public Page<CategoryDto> getAll(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @Operation(
            summary = "Get a category by ID",
            description = "Fetches the details of a category by its ID."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MARKET_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    public CategoryDto getCategory(@PathVariable @Positive Long id) {
        return categoryService.get(id);
    }

    @Operation(
            summary = "Update a category by ID",
            description = "Updates the details of a specific category."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MARKET_ADMIN')")
    @PutMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable @Positive Long id,
                                      @RequestBody @Valid UpdateCategoryRequestDto requestDto) {
        return categoryService.update(id, requestDto);
    }

    @Operation(
            summary = "Delete a category by ID",
            description = "Deletes a category by its ID."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MARKET_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable @Positive Long id) {
        categoryService.delete(id);
    }
}
