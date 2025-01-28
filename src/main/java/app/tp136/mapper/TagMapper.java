package app.tp136.mapper;

import app.tp136.config.MapperConfig;
import app.tp136.dto.TagDto;
import app.tp136.model.Tag;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Mapper(config = MapperConfig.class)
public interface TagMapper {
    Tag toTag(TagDto dto);

    TagDto toDto(Tag tag);

    List<TagDto> toTagDtoList(List<Tag> tags);

    void updateTag(TagDto dto, @MappingTarget Tag tag);

    default Page<TagDto> toTagDtoPage(Page<Tag> tags) {
        List<TagDto> dtos = toTagDtoList(tags.getContent());
        return new PageImpl<>(dtos, tags.getPageable(), tags.getTotalElements());
    }
}
