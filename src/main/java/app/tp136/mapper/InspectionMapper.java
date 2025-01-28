package app.tp136.mapper;

import app.tp136.config.MapperConfig;
import app.tp136.dto.InspectionDto;
import app.tp136.dto.responce.InspectionResponseDto;
import app.tp136.model.Inspection;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Mapper(config = MapperConfig.class)
public interface InspectionMapper {
    Inspection toInspection(InspectionDto dto);

    InspectionDto toDto(Inspection inspection);

    InspectionResponseDto toResponseDto(Inspection inspection);

    List<InspectionResponseDto> toDtoList(List<Inspection> inspections);

    default Page<InspectionResponseDto> toDtoPage(Page<Inspection> inspections) {
        List<InspectionResponseDto> dtos = toDtoList(inspections.getContent());
        return new PageImpl<>(dtos, inspections.getPageable(), inspections.getTotalElements());
    }
}
