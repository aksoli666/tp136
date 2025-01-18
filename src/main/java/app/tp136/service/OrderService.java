package app.tp136.service;

import app.tp136.dto.OrderItemDto;
import app.tp136.dto.request.PlaceOrderRequestDto;
import app.tp136.dto.responce.OrderResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface OrderService {
    OrderResponseDto placeOrder(Authentication authentication, PlaceOrderRequestDto dto);

    OrderResponseDto getOrderHistory(Authentication authentication, Long orderId);

    Page<OrderItemDto> getOrderItems(Authentication authentication,
                                     Long orderId, Pageable pageable);

    OrderItemDto getOrderItem(Authentication authentication, Long orderId, Long itemId);
}
