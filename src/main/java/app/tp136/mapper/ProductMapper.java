package app.tp136.mapper;

import app.tp136.config.MapperConfig;
import app.tp136.dto.ProductDto;
import app.tp136.dto.ProductWithoutCategoryDto;
import app.tp136.dto.request.CreateProductRequestDto;
import app.tp136.dto.request.UpdateProductRequestDto;
import app.tp136.model.Category;
import app.tp136.model.Product;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Mapper(config = MapperConfig.class)
public interface ProductMapper {
    Product toProduct(ProductDto dto);

    Product toProduct(CreateProductRequestDto dto);

    ProductDto toDto(Product product);

    List<ProductDto> toDtoList(List<Product> products);

    void update(UpdateProductRequestDto dto, @MappingTarget Product product);

    List<ProductWithoutCategoryDto> toWithoutCategoryDtoList(List<Product> products);

    default Page<ProductDto> toDtoPage(Page<Product> products) {
        List<ProductDto> dtos = toDtoList(products.getContent());
        return new PageImpl<>(dtos, products.getPageable(), products.getTotalElements());
    }

    default Page<ProductWithoutCategoryDto> toWithoutCategoryDtoPage(Page<Product> products) {
        List<ProductWithoutCategoryDto> dtos = toWithoutCategoryDtoList(products.getContent());
        return new PageImpl<>(dtos, products.getPageable(), products.getTotalElements());
    }

    @AfterMapping
    default void setCategoryIds(@MappingTarget ProductDto dto, Product product) {
        Set<Category> categories = product.getCategories();
        Set<Long> categoryIds = new HashSet<>();
        for (Category category : categories) {
            categoryIds.add(category.getId());
        }
        dto.setCategoryIds(categoryIds);
    }
}
