package app.tp136.repository;

import app.tp136.model.ShoppingCart;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @EntityGraph(attributePaths = {"user", "cartItems", "cartItems.product"})
    Optional<ShoppingCart> findByUserId(Long userId);

    @EntityGraph(attributePaths = "cartItems")
    Optional<ShoppingCart> findShoppingCartById(Long id);
}
