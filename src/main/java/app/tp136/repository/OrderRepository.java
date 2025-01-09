package app.tp136.repository;

import app.tp136.model.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
    @EntityGraph(attributePaths = {"orderItems", "status"})
    Optional<Order> findOrderById(Long id);

    @EntityGraph(attributePaths = {"orderItems", "status"})
    Optional<Order> findOrderByUserId(Long userId);
}
