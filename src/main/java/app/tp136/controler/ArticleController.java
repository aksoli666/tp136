package app.tp136.controler;

import app.tp136.dto.ArticleDto;
import app.tp136.dto.request.CreateArticleRequestDto;
import app.tp136.dto.request.UpdateArticleRequestDto;
import app.tp136.service.ArticleService;
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

@Tag(name = "Article management", description = "Endpoints for managing articles")
@RestController
@RequestMapping(value = "/articles", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @Operation(
            summary = "Create a new article",
            description = "Create a new article with the provided details."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COPYWRITER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ArticleDto createArticle(@RequestBody @Valid CreateArticleRequestDto dto) {
        return articleService.save(dto);
    }

    @Operation(
            summary = "Get all articles",
            description = "Retrieves a pageable list of all articles."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COPYWRITER', 'ROLE_USER')")
    @GetMapping
    public Page<ArticleDto> getAll(Pageable pageable) {
        return articleService.getAll(pageable);
    }

    @Operation(
            summary = "Get a article by ID",
            description = "Fetches the details of a article by its ID."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COPYWRITER', 'ROLE_USER')")
    @GetMapping("/{id}")
    public ArticleDto getArticle(@PathVariable @Positive Long id) {
        return articleService.get(id);
    }

    @Operation(
            summary = "Update a article by ID",
            description = "Updates the details of a specific article."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COPYWRITER')")
    @PutMapping("/{id}")
    public ArticleDto updateArticle(@PathVariable @Positive Long id,
                                    @RequestBody @Valid UpdateArticleRequestDto dto) {
        return articleService.update(id, dto);
    }

    @Operation(
            summary = "Delete a article by ID",
            description = "Deletes a article by its ID."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COPYWRITER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable @Positive Long id) {
        articleService.delete(id);
    }
}
