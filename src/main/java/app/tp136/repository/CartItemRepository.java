package app.tp136.repository;

import app.tp136.model.CartItem;
import app.tp136.model.Product;
import app.tp136.model.ShoppingCart;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("SELECT ci FROM CartItem ci "
            + "WHERE ci.shoppingCart = :shoppingCart "
            + "AND ci.product = :product AND ci.isDeleted = false")
    Optional<CartItem> findByShoppingCartAndProduct(ShoppingCart shoppingCart, Product product);
}
