package app.tp136.controler;

import app.tp136.dto.OrderItemDto;
import app.tp136.dto.StatusDto;
import app.tp136.dto.request.PlaceOrderRequestDto;
import app.tp136.dto.responce.OrderResponseDto;
import app.tp136.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order management", description = "Endpoints for managing orders")
@RestController
@RequestMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @Operation(
            summary = "Create a new order",
            description = "Places a new order using the provided details."
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public OrderResponseDto placeOrder(Authentication authentication,
                                       @RequestBody @Valid PlaceOrderRequestDto requestDto) {
        return orderService.placeOrder(authentication, requestDto);
    }

    @Operation(
            summary = "Retrieve order history",
            description = "Fetches the authenticated user's order history."
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public OrderResponseDto getOrderHistory(Authentication authentication) {
        return orderService.getOrderHistory(authentication);
    }

    @Operation(
            summary = "Update order status",
            description = "Updates the status of an order with the given ID."
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MARKET_ADMIN')")
    @PutMapping("/{id}")
    public OrderResponseDto updateOrderStatus(Authentication authentication,
                                              @PathVariable @Positive Long id,
                                              @RequestBody @Valid StatusDto statusDto) {
        return orderService.updateOrderStatus(authentication, id, statusDto);
    }

    @Operation(
            summary = "Retrieve order items",
            description = "Fetches all items associated with a specific order."
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{orderId}/items")
    public Page<OrderItemDto> getOrderItems(Authentication authentication,
                                            @PathVariable @Positive Long orderId,
                                            Pageable pageable) {
        return orderService.getOrderItems(authentication, orderId, pageable);
    }

    @Operation(
            summary = "Retrieve a specific order item",
            description = "Fetches details of a specific item within an order."
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{orderId}/items/{itemId}")
    public OrderItemDto getOrderItem(Authentication authentication,
                                     @PathVariable @Positive Long orderId,
                                     @PathVariable @Positive Long itemId) {
        return orderService.getOrderItem(authentication, orderId, itemId);
    }
}
