package app.tp136.mapper;

import app.tp136.config.MapperConfig;
import app.tp136.dto.CommentDto;
import app.tp136.dto.request.CreateCommentRequestDto;
import app.tp136.model.Comment;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Mapper(config = MapperConfig.class)
public interface CommentMapper {
    Comment toComment(CommentDto dto);

    Comment toComment(CreateCommentRequestDto dto);

    @Mapping(source = "user.firstName", target = "userFirstName")
    @Mapping(source = "user.lastName", target = "userLastName")
    CommentDto toDto(Comment comment);

    List<CommentDto> toDtoList(List<Comment> comments);

    default Page<CommentDto> toDtoPage(Page<Comment> comments) {
        List<CommentDto> dtos = toDtoList(comments.getContent());
        return new PageImpl<>(dtos, comments.getPageable(), comments.getTotalElements());
    }
}
