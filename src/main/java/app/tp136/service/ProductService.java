package app.tp136.service;

import app.tp136.dto.ProductDto;
import app.tp136.dto.ProductSearchParamsDto;
import app.tp136.dto.ProductWithoutCategoryDto;
import app.tp136.dto.request.CreateProductRequestDto;
import app.tp136.dto.request.UpdateProductRequestDto;
import java.math.BigDecimal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductDto save(CreateProductRequestDto dto);

    ProductDto get(Long id);

    Page<ProductDto> getByPriceBetween(BigDecimal low, BigDecimal high, Pageable pageable);

    Page<ProductDto> getAll(Pageable pageable);

    Page<ProductDto> search(ProductSearchParamsDto dto, Pageable pageable);

    Page<ProductWithoutCategoryDto> getByCategoryId(Long categoryId, Pageable pageable);

    Page<ProductDto> getAllSortedByNameAsc(Pageable pageable);

    Page<ProductDto> getAllSortedByNameDesc(Pageable pageable);

    Page<ProductDto> getEarliestPublicationDate(Pageable pageable);

    Page<ProductDto> getLatestPublicationDate(Pageable pageable);

    ProductDto update(Long id, UpdateProductRequestDto dto);

    void delete(Long id);
}
