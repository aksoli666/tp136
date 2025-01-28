package app.tp136.service;

import app.tp136.dto.CommentDto;
import app.tp136.dto.DiscussionDto;
import app.tp136.dto.request.CreateCommentRequestDto;
import app.tp136.dto.request.CreateDiscussionRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface DiscussionService {
    DiscussionDto save(Authentication authentication,
                        CreateDiscussionRequestDto dto);

    DiscussionDto getDiscussion(Long id);

    Page<DiscussionDto> getDiscussions(Pageable pageable);

    void delete(Long id);

    CommentDto addComment(Authentication authentication,
                          Long discussionId,
                          CreateCommentRequestDto dto);

    CommentDto getComment(Long id);

    Page<CommentDto> getComments(Pageable pageable);

    void deleteComment(Long id);
}
