package app.tp136.mapper;

import app.tp136.config.MapperConfig;
import app.tp136.dto.CategoryDto;
import app.tp136.dto.request.CreateCategoryRequestDto;
import app.tp136.dto.request.UpdateCategoryRequestDto;
import app.tp136.model.Category;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    Category toCategory(CreateCategoryRequestDto requestDto);

    Category toCategory(CategoryDto categoryDto);

    CategoryDto toCategoryDto(Category category);

    List<CategoryDto> toCategoryDtoList(List<Category> categoryPage);

    void updateCategory(UpdateCategoryRequestDto requestDto, @MappingTarget Category category);

    default Page<CategoryDto> toCategoryDtoPage(Page<Category> categoryPage) {
        List<CategoryDto> dtos = toCategoryDtoList(categoryPage.getContent());
        return new PageImpl<>(dtos, categoryPage.getPageable(), categoryPage.getTotalElements());
    }
}
