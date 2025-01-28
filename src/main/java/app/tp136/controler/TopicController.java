package app.tp136.controler;

import app.tp136.dto.TopicDto;
import app.tp136.service.TopicService;
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

@Tag(name = "Topic management", description = "Endpoints for managing topics")
@RestController
@RequestMapping(value = "/topics", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;

    @Operation(
            summary = "Create a new topic",
            description = "Creates a new topic with the provided details."
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TopicDto createTopic(@RequestBody @Valid TopicDto dto) {
        return topicService.save(dto);
    }

    @Operation(
            summary = "Get all topics",
            description = "Retrieves a pageable list of all topics."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public Page<TopicDto> getAll(Pageable pageable) {
        return topicService.getAll(pageable);
    }

    @Operation(
            summary = "Get a topic by ID",
            description = "Fetches the details of a topic by its ID."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    public TopicDto getTopic(@PathVariable @Positive Long id) {
        return topicService.get(id);
    }

    @Operation(
            summary = "Update a topic by ID",
            description = "Updates the details of a specific topic."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public TopicDto updateTopic(@PathVariable @Positive Long id,
                                @Valid @RequestBody TopicDto dto) {
        return topicService.update(id, dto);
    }

    @Operation(
            summary = "Delete a topic by ID",
            description = "Deletes a topic by its ID."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTopic(@PathVariable @Positive Long id) {
        topicService.delete(id);
    }
}
