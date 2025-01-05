package app.tp136.controler;

import app.tp136.dto.ProductDto;
import app.tp136.dto.ProductSearchParamsDto;
import app.tp136.dto.ProductWithoutCategoryDto;
import app.tp136.dto.request.CreateProductRequestDto;
import app.tp136.dto.request.UpdateProductRequestDto;
import app.tp136.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Product management", description = "Endpoints for managing products")
@RestController
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @Operation(
            summary = "Create a new product",
            description = "Creates a new product with the provided details."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MARKET_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ProductDto createProduct(@RequestBody @Valid CreateProductRequestDto dto) {
        return productService.save(dto);
    }

    @Operation(
            summary = "Retrieve a product by ID",
            description = "Fetches a product by its unique ID."
    )
    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable @Positive Long id) {
        return productService.get(id);
    }

    @Operation(
            summary = "Get products by price range",
            description = "Fetches products within the specified price range."
    )
    @GetMapping("/by-price")
    public Page<ProductDto> getProductByPriceBetween(
            @RequestParam @DecimalMin("0.0") BigDecimal low,
            @RequestParam BigDecimal high,
            Pageable pageable) {
        return productService.getByPriceBetween(low, high, pageable);
    }

    @Operation(
            summary = "Get all products",
            description = "Retrieves all products as a pageable list."
    )
    @GetMapping()
    public Page<ProductDto> getAll(Pageable pageable) {
        return productService.getAll(pageable);
    }

    @Operation(
            summary = "Search products",
            description = "Searches for products based on specified search criteria."
    )
    @GetMapping("/search")
    public Page<ProductDto> search(@RequestBody @Valid ProductSearchParamsDto dto,
                                   Pageable pageable) {
        return productService.search(dto, pageable);
    }

    @Operation(
            summary = "Get products by category ID",
            description = "Retrieves products belonging to a specific category."
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/category/{categoryId}")
    public Page<ProductWithoutCategoryDto> getProductByCategoryId(
            @PathVariable @Positive Long categoryId, Pageable pageable) {
        return productService.getByCategoryId(categoryId, pageable);
    }

    @Operation(
            summary = "Get products by alphabetical order (ascending)",
            description = "Retrieves products sorted by name in ascending alphabetical order."
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/category/name/ascending")
    public Page<ProductDto> getAllSortedByNameAsc(Pageable pageable) {
        return productService.getAllSortedByNameAsc(pageable);
    }

    @Operation(
            summary = "Get products by reverse alphabetical order (descending)",
            description = "Retrieves products sorted by name in descending alphabetical order."
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/category/name/descending")
    public Page<ProductDto> getAllSortedByNameDesc(Pageable pageable) {
        return productService.getAllSortedByNameDesc(pageable);
    }

    @Operation(
            summary = "Get products by earliest publication date",
            description = "Retrieves products sorted by the earliest publication date."
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/category/earliest")
    public Page<ProductDto> getEarliestPublicationDate(Pageable pageable) {
        return productService.getEarliestPublicationDate(pageable);
    }

    @Operation(
            summary = "Get products by latest publication date",
            description = "Retrieves products sorted by the latest publication date."
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/category/latest")
    public Page<ProductDto> getLatestPublicationDate(Pageable pageable) {
        return productService.getLatestPublicationDate(pageable);
    }

    @Operation(
            summary = "Update a product",
            description = "Updates the details of an existing product."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MARKET_ADMIN')")
    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable Long id,
                                    @RequestBody @Valid UpdateProductRequestDto dto) {
        return productService.update(id, dto);
    }

    @Operation(
            summary = "Delete a product",
            description = "Deletes a product by its ID."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MARKET_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable @Positive Long id) {
        productService.delete(id);
    }
}
