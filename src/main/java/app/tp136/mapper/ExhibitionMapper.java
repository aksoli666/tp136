package app.tp136.mapper;

import app.tp136.config.MapperConfig;
import app.tp136.dto.ExhibitionDto;
import app.tp136.model.Exhibition;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Mapper(config = MapperConfig.class)
public interface ExhibitionMapper {
    Exhibition toExhibition(ExhibitionDto dto);

    ExhibitionDto toDto(Exhibition entity);

    List<ExhibitionDto> toDtoList(List<Exhibition> entities);

    void updateExhibition(ExhibitionDto dto, @MappingTarget Exhibition exhibition);

    default Page<ExhibitionDto> toDtoPage(Page<Exhibition> entities) {
        List<ExhibitionDto> dtos = toDtoList(entities.getContent());
        return new PageImpl<>(dtos, entities.getPageable(), entities.getTotalElements());
    }
}
