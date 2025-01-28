package app.tp136.mapper;

import app.tp136.config.MapperConfig;
import app.tp136.dto.TopicDto;
import app.tp136.model.Topic;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Mapper(config = MapperConfig.class)
public interface TopicMapper {
    Topic toTopic(TopicDto dto);

    TopicDto toTopicDto(Topic topic);

    List<TopicDto> toTopicDtoList(List<Topic> topics);

    void updateTopic(TopicDto dto, @MappingTarget Topic topic);

    default Page<TopicDto> toTopicDtoPage(Page<Topic> topics) {
        List<TopicDto> dtos = toTopicDtoList(topics.getContent());
        return new PageImpl<>(dtos, topics.getPageable(), topics.getTotalElements());
    }
}
