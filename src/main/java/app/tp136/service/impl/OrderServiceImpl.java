package app.tp136.service.impl;

import app.tp136.dto.OrderItemDto;
import app.tp136.dto.request.PlaceOrderRequestDto;
import app.tp136.dto.responce.CartItemResponseDto;
import app.tp136.dto.responce.OrderResponseDto;
import app.tp136.dto.responce.ShoppingCartResponseDto;
import app.tp136.exception.EntityNotFoundException;
import app.tp136.mapper.OrderItemMapper;
import app.tp136.mapper.OrderMapper;
import app.tp136.model.Order;
import app.tp136.model.OrderItem;
import app.tp136.model.Product;
import app.tp136.model.User;
import app.tp136.repository.OrderItemRepository;
import app.tp136.repository.OrderRepository;
import app.tp136.repository.ProductRepository;
import app.tp136.security.CustomUserDetailsService;
import app.tp136.service.OrderService;
import app.tp136.service.ShoppingCartService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CustomUserDetailsService userDetailsService;
    private final ShoppingCartService shoppingCartService;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderMapper orderMapper;

    @Transactional
    @Override
    public OrderResponseDto placeOrder(Authentication authentication,
                                       PlaceOrderRequestDto dto) {
        log.info("Placing order for user: {}", authentication.getName());

        User user = (User) userDetailsService.loadUserByUsername(authentication.getName());

        Order order = createOrder(user, dto);
        Set<OrderItem> orderItems = createOrderItems(authentication, order);
        log.info("Order items created successfully for order ID: {}", order.getId());

        order.setOrderItems(orderItems);
        order.setTotal(getTotal(orderItems));

        orderRepository.save(order);
        log.info("Order saved successfully with ID: {}", order.getId());
        orderItemRepository.saveAll(orderItems);
        log.info("Order items saved successfully for order ID: {}", order.getId());

        return orderMapper.toDto(order);
    }

    @Override
    public OrderResponseDto getOrderHistory(Authentication authentication, Long orderId) {
        Long userId = userDetailsService.getUserIdFromAuthentication(authentication);
        return orderMapper.toDto(
                orderRepository.findOrderByIdAndUserId(orderId, userId)
                        .orElseThrow(() -> {
                            log.warn("Order not found for user ID: {} and order ID: {}",
                                    userId, orderId);
                            return new EntityNotFoundException("Can't find order");
                        })
        );
    }

    @Override
    public Page<OrderItemDto> getOrderItems(Authentication authentication,
                                            Long orderId,
                                            Pageable pageable) {
        Long userId = userDetailsService.getUserIdFromAuthentication(authentication);
        Page<OrderItem> orderItems = orderItemRepository
                .findByOrderIdAndOrderUserId(orderId, userId, pageable);
        return orderItems.map(orderItemMapper::toDto);
    }

    @Override
    public OrderItemDto getOrderItem(Authentication authentication, Long orderId, Long itemId) {
        Long userId = userDetailsService.getUserIdFromAuthentication(authentication);
        List<OrderItem> orderItems = orderItemRepository
                .findByOrderIdAndOrderUserId(orderId, userId);
        OrderItem orderItem = orderItems.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> {
                    log.warn("Order item not found. Order ID: {}, item ID: {}", orderId, itemId);
                    return new EntityNotFoundException("Can't find order item. Id " + itemId);
                });
        return orderItemMapper.toDto(orderItem);
    }

    private Order createOrder(User user, PlaceOrderRequestDto dto) {
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setFirstName(dto.getFirstName());
        order.setLastName(dto.getLastName());
        order.setCountry(dto.getCountry());
        order.setCity(dto.getCity());
        order.setPost(dto.getPost());
        order.setDepartment(dto.getDepartment());

        log.info("Order details set for user: {}", user.getEmail());
        return order;
    }

    private Set<OrderItem> createOrderItems(Authentication authentication, Order order) {
        Set<OrderItem> orderItems = new HashSet<>();
        ShoppingCartResponseDto shoppingCart = shoppingCartService
                .getShoppingCartById(authentication);
        Set<CartItemResponseDto> cartItems = shoppingCart.getCartItems();
        for (CartItemResponseDto cartItem : cartItems) {
            Product product = productRepository.findById(cartItem.getProductId())
                    .orElseThrow(() -> {
                        log.warn("Product not found. ID: {}", cartItem.getProductId());
                        return new EntityNotFoundException("Can't find product. Id: "
                                + cartItem.getProductId());
                    });
            BigDecimal price = product.getPrice();
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(price.multiply(BigDecimal.valueOf(
                    cartItem.getQuantity())));

            log.info("Order item created for product ID: {}, quantity: {}",
                    product.getId(), cartItem.getQuantity());
            orderItems.add(orderItem);
        }
        log.info("Total of {} order items created for order ID: {}",
                orderItems.size(), order.getId());
        return orderItems;
    }

    private BigDecimal getTotal(Set<OrderItem> orderItems) {
        return orderItems.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
