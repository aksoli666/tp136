package app.tp136.repository;

import app.tp136.model.ShoppingCart;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @Query("SELECT sc FROM ShoppingCart sc JOIN FETCH sc.user WHERE sc.user.id = :userId")
    @EntityGraph(attributePaths = {"cartItems", "cartItems.product"})
    Optional<ShoppingCart> findByUserId(Long userId);

    @EntityGraph(attributePaths = "cartItems")
    Optional<ShoppingCart> findShoppingCartById(Long id);
}
