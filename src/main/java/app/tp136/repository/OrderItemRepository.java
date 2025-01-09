package app.tp136.repository;

import app.tp136.model.OrderItem;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Page<OrderItem> findByOrderIdAndOrderUserId(Long orderId, Long userId, Pageable pageable);

    List<OrderItem> findByOrderIdAndOrderUserId(Long orderId, Long userId);
}
