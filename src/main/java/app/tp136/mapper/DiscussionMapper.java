package app.tp136.mapper;

import app.tp136.config.MapperConfig;
import app.tp136.dto.DiscussionDto;
import app.tp136.dto.request.CreateDiscussionRequestDto;
import app.tp136.model.Discussion;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Mapper(config = MapperConfig.class, uses = {CommentMapper.class, TopicMapper.class})
public interface DiscussionMapper {
    Discussion toDiscussion(CreateDiscussionRequestDto dto);

    Discussion toDiscussion(DiscussionDto dto);

    @Mapping(source = "user.firstName", target = "userFirstName")
    @Mapping(source = "user.lastName", target = "userLastName")
    @Mapping(source = "countComments", target = "countComments")
    DiscussionDto toDto(Discussion discussion);

    List<DiscussionDto> toDtoList(List<Discussion> discussions);

    default Page<DiscussionDto> toDtoPage(Page<Discussion> discussions) {
        List<DiscussionDto> dtos = toDtoList(discussions.getContent());
        return new PageImpl<>(dtos, discussions.getPageable(), discussions.getTotalElements());
    }
}
