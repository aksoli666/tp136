package app.tp136.service.impl;

import app.tp136.dto.ProductDto;
import app.tp136.dto.ProductSearchParamsDto;
import app.tp136.dto.ProductWithoutCategoryDto;
import app.tp136.dto.request.CreateProductRequestDto;
import app.tp136.dto.request.UpdateProductRequestDto;
import app.tp136.exception.EntityNotFoundException;
import app.tp136.mapper.ProductMapper;
import app.tp136.model.Category;
import app.tp136.model.Product;
import app.tp136.repository.CategoryRepository;
import app.tp136.repository.ProductRepository;
import app.tp136.service.ProductService;
import app.tp136.spec.tools.impl.ProductSpecificationBuilder;
import java.math.BigDecimal;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final ProductSpecificationBuilder productSpecificationBuilder;

    @Transactional
    @Override
    public ProductDto save(CreateProductRequestDto dto) {
        Product product = productMapper.toProduct(dto);
        fetchCategoriesAndSetToProduct(dto.getCategoryIds(), product);
        log.info("Product saved successfully with ID: {}", product.getId());
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public ProductDto get(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> {
            log.warn("Product not found for ID: {}", id);
            return new EntityNotFoundException("Can't find product. Id: " + id);
        });
        return productMapper.toDto(product);
    }

    @Override
    public Page<ProductDto> getByPriceBetween(BigDecimal low, BigDecimal high, Pageable pageable) {
        if (low == null) {
            low = BigDecimal.ZERO;
        }
        return productMapper.toDtoPage(
                productRepository.findByPriceBetween(low, high, pageable));
    }

    @Override
    public Page<ProductDto> getAll(Pageable pageable) {
        return productMapper.toDtoPage(productRepository.findAll(pageable));
    }

    @Override
    public Page<ProductDto> search(ProductSearchParamsDto dto, Pageable pageable) {
        return productMapper.toDtoPage(
                productRepository.findAll(
                        productSpecificationBuilder.build(dto), pageable));
    }

    @Override
    public Page<ProductWithoutCategoryDto> getByCategoryId(Long categoryId, Pageable pageable) {
        return productMapper.toWithoutCategoryDtoPage(
                productRepository.findAllByCategoryId(categoryId, pageable));
    }

    @Override
    public Page<ProductDto> getAllSortedByNameAscUa(Pageable pageable) {
        return productMapper.toDtoPage(
                productRepository.findAllSortedByNameAscendingUa(pageable));
    }

    @Override
    public Page<ProductDto> getAllSortedByNameAscEng(Pageable pageable) {
        return productMapper.toDtoPage(
                productRepository.findAllSortedByNameAscendingEng(pageable));
    }

    @Override
    public Page<ProductDto> getAllSortedByNameDescUa(Pageable pageable) {
        return productMapper.toDtoPage(
                productRepository.findAllSortedByNameDescendingUa(pageable));
    }

    @Override
    public Page<ProductDto> getAllSortedByNameDescEng(Pageable pageable) {
        return productMapper.toDtoPage(
                productRepository.findAllSortedByNameDescendingEng(pageable));
    }

    @Override
    public Page<ProductDto> getEarliestPublicationDate(Pageable pageable) {
        return productMapper.toDtoPage(
                productRepository.findAllSortedByEarliestPublicationDate(pageable));
    }

    @Override
    public Page<ProductDto> getLatestPublicationDate(Pageable pageable) {
        return productMapper.toDtoPage(
                productRepository.findAllSortedByLatestPublicationDate(pageable));
    }

    @Transactional
    @Override
    public ProductDto update(Long id, UpdateProductRequestDto dto) {
        Product product = productRepository.findById(id).orElseThrow(() -> {
            log.warn("Product not found for update. ID: {}", id);
            return new EntityNotFoundException("Can't get category. Id: " + id);
        });
        fetchCategoriesAndSetToProduct(dto.getCategoryIds(), product);
        productMapper.update(dto, product);
        return productMapper.toDto(productRepository.save(product));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    private void fetchCategoriesAndSetToProduct(Set<Long> categoryIds, Product product) {
        Set<Category> categories = categoryRepository.findByIds(categoryIds);
        product.setCategories(categories);
    }
}
