package app.tp136.service.impl;

import app.tp136.dto.CommentDto;
import app.tp136.dto.DiscussionDto;
import app.tp136.dto.request.CreateCommentRequestDto;
import app.tp136.dto.request.CreateDiscussionRequestDto;
import app.tp136.exception.EntityNotFoundException;
import app.tp136.mapper.CommentMapper;
import app.tp136.mapper.DiscussionMapper;
import app.tp136.model.Comment;
import app.tp136.model.Discussion;
import app.tp136.model.Topic;
import app.tp136.model.User;
import app.tp136.repository.CommentRepository;
import app.tp136.repository.DiscussionRepository;
import app.tp136.repository.TopicRepository;
import app.tp136.security.CustomUserDetailsService;
import app.tp136.service.DiscussionService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DiscussionServiceImpl implements DiscussionService {
    private final TopicRepository topicRepository;
    private final CommentRepository commentRepository;
    private final DiscussionMapper discussionMapper;
    private final CommentMapper commentMapper;
    private final CustomUserDetailsService customUserDetailsService;
    private final DiscussionRepository discussionRepository;

    @Transactional
    @Override
    public DiscussionDto save(Authentication authentication,
                              CreateDiscussionRequestDto dto) {
        User user = customUserDetailsService.getUserFromAuthentication(authentication);
        Discussion discussion = discussionMapper.toDiscussion(dto);
        discussion.setUser(user);
        fetchTopicsAndSetToDiscussion(dto.getTopicIds(), discussion);
        return discussionMapper.toDto(discussionRepository.save(discussion));
    }

    @Override
    public DiscussionDto getDiscussion(Long id) {
        Discussion discussion = discussionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can`t find discussion. Id: " + id));
        return discussionMapper.toDto(discussion);
    }

    @Override
    public Page<DiscussionDto> getDiscussions(Pageable pageable) {
        return discussionMapper.toDtoPage(
                discussionRepository.findAll(pageable));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        discussionRepository.deleteById(id);
    }

    @Transactional
    @Override
    public CommentDto addComment(Authentication authentication,
                                 Long discussionId,
                                 CreateCommentRequestDto dto) {
        User user = customUserDetailsService.getUserFromAuthentication(authentication);
        Comment comment = commentMapper.toComment(dto);
        comment.setUser(user);
        Discussion discussion = discussionRepository.findById(discussionId).orElseThrow(
                () -> new EntityNotFoundException("Can`t find discussion. Id: "
                        + discussionId));
        comment.setDiscussion(discussion);
        discussion.getComments().add(comment);
        discussion.setCountComments(discussion.getComments().size());
        discussionRepository.save(discussion);
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toDto(savedComment);
    }

    @Override
    public CommentDto getComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can`t find comment. Id: " + id));
        return commentMapper.toDto(comment);
    }

    @Override
    public Page<CommentDto> getComments(Pageable pageable) {
        return commentMapper.toDtoPage(
                commentRepository.findAll(pageable));
    }

    @Transactional
    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    private void fetchTopicsAndSetToDiscussion(Set<Long> topicIds, Discussion discussion) {
        Set<Topic> topics = topicRepository.findByIds(topicIds);
        discussion.setTopics(topics);
    }
}
