package app.tp136.repository;

import app.tp136.model.Product;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @EntityGraph(attributePaths = "categories")
    Optional<Product> findById(Long id);

    @EntityGraph(attributePaths = "categories")
    Page<Product> findAll(Pageable pageable);

    @EntityGraph(attributePaths = "categories")
    Page<Product> findAll(Specification<Product> spec, Pageable pageable);

    @Query("SELECT p FROM Product p "
            + "JOIN FETCH p.categories c "
            + "WHERE p.price >= :low AND (:high IS NULL OR p.price <= :high)")
    Page<Product> findByPriceBetween(BigDecimal low, BigDecimal high, Pageable pageable);

    @Query("SELECT p FROM Product p "
            + "JOIN FETCH p.categories c "
            + "WHERE c.id = :categoryId AND p.isDeleted = false AND c.isDeleted = false")
    Page<Product> findAllByCategoryId(Long categoryId, Pageable pageable);
}
