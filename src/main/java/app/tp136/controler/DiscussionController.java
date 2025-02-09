package app.tp136.controler;

import app.tp136.dto.CommentDto;
import app.tp136.dto.DiscussionDto;
import app.tp136.dto.request.CreateCommentRequestDto;
import app.tp136.dto.request.CreateDiscussionRequestDto;
import app.tp136.service.DiscussionService;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Discussion management", description = "Endpoints for managing discussions")
@RestController
@RequestMapping(value = "/discussions", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class DiscussionController {
    private final DiscussionService discussionService;

    @Operation(
            summary = "Create a new discussion",
            description = "Creates a new discussion with the provided details."
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    DiscussionDto createDiscussion(Authentication authentication,
                                   @RequestBody @Valid CreateDiscussionRequestDto dto) {
        return discussionService.save(authentication, dto);
    }

    @Operation(
            summary = "Get a discussion by ID",
            description = "Fetches the details of a discussion by its ID."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    DiscussionDto getDiscussion(@PathVariable @Positive Long id) {
        return discussionService.getDiscussion(id);
    }

    @Operation(
            summary = "Get a discussion by topic ID",
            description = "Fetches the details of a discussion by its topic ID."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/topic")
    Page<DiscussionDto> getAllByTopic(@RequestParam @Positive Long topicId, Pageable pageable) {
        return discussionService.findDiscussionByTopicName(topicId, pageable);
    }

    @Operation(
            summary = "Get all discussions",
            description = "Retrieves a pageable list of all discussions."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    Page<DiscussionDto> getDiscussions(Pageable pageable) {
        return discussionService.getDiscussions(pageable);
    }

    @Operation(
            summary = "Delete a discussion by ID",
            description = "Deletes a discussion by its ID."
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteDiscussion(@PathVariable @Positive Long id) {
        discussionService.delete(id);
    }

    @Operation(
            summary = "Add a new comment",
            description = "Adds a new comment to discussing with the provided details."
    )
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add-comment/{discussionId}")
    CommentDto addComment(Authentication authentication,
                          @PathVariable @Positive Long discussionId,
                          @RequestBody @Valid CreateCommentRequestDto dto) {
        return discussionService.addComment(authentication, discussionId, dto);
    }

    @Operation(
            summary = "Get a comment by ID",
            description = "Fetches the details of a comment by its ID."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/comments/{id}")
    CommentDto getComment(@PathVariable @Positive Long id) {
        return discussionService.getComment(id);
    }

    @Operation(
            summary = "Get all comments",
            description = "Retrieves a pageable list of all comments."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/comments")
    Page<CommentDto> getComments(Pageable pageable) {
        return discussionService.getComments(pageable);
    }

    @Operation(
            summary = "Delete a comment by ID",
            description = "Deletes a comment by its ID."
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/comments/{id}")
    void deleteComment(@PathVariable @Positive Long id) {
        discussionService.delete(id);
    }
}
