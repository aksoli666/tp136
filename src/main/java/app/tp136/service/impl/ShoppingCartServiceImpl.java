package app.tp136.service.impl;

import app.tp136.dto.request.AddProductToCartRequestDto;
import app.tp136.dto.request.UpdateQuantityProductRequestDto;
import app.tp136.dto.responce.ShoppingCartResponseDto;
import app.tp136.exception.EntityNotFoundException;
import app.tp136.mapper.ShoppingCartMapper;
import app.tp136.model.CartItem;
import app.tp136.model.Product;
import app.tp136.model.ShoppingCart;
import app.tp136.model.User;
import app.tp136.repository.CartItemRepository;
import app.tp136.repository.ProductRepository;
import app.tp136.repository.ShoppingCartRepository;
import app.tp136.security.CustomUserDetailsService;
import app.tp136.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final CustomUserDetailsService customUserDetailsService;
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    @Transactional
    @Override
    public void createShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartRepository.save(shoppingCart);
        log.info("Shopping cart created successfully for user with id: {}", user.getId());
    }

    @Transactional
    @Override
    public ShoppingCartResponseDto getShoppingCartById(Authentication authentication) {
        Long userId = customUserDetailsService.getUserIdFromAuthentication(authentication);
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    log.error("Shopping cart not found for user with id: {}", userId);
                    return new RuntimeException("Can't find shopping cart for this user. Id: "
                            + userId);
                });
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Transactional
    @Override
    public ShoppingCartResponseDto addProductToShoppingCart(Authentication authentication,
                                                            AddProductToCartRequestDto dto) {
        Long userId = customUserDetailsService.getUserIdFromAuthentication(authentication);
        Long productId = dto.getProductId();
        log.info("Adding product with id: {} to shopping cart for user with id: {}",
                productId, userId);

        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartById(userId)
                .orElseThrow(() -> {
                    log.error("Shopping cart not found for user with id: {}", userId);
                    return new EntityNotFoundException("Can't find shopping cart "
                            + "for this user. Id: " + userId);
                });
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    log.error("Product not found with id: {}", productId);
                    return new EntityNotFoundException("Can't find product. Id: " + productId);
                });
        cartItemRepository.findByShoppingCartAndProduct(shoppingCart, product)
                .ifPresent(item -> {
                    log.error("Product already exists in shopping cart. Id: {}", productId);
                    throw new IllegalArgumentException("Product already exists. Id: " + productId);
                });
        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(shoppingCart);
        cartItem.setQuantity(dto.getQuantity());
        cartItem.setProduct(product);
        shoppingCart.getCartItems().add(cartItem);
        shoppingCartRepository.save(shoppingCart);

        log.info("Product with id: {} added successfully "
                + "to shopping cart for user with id: {}", productId, userId);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Transactional
    @Override
    public ShoppingCartResponseDto updateQuantityProduct(Authentication authentication,
                                                         Long cartItemId,
                                                         UpdateQuantityProductRequestDto dto) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> {
                    log.error("Cart item not found with id: {}", cartItemId);
                    return new EntityNotFoundException("Can't get cart item. Id: " + cartItemId);
                });
        cartItem.setQuantity(dto.quantity());
        cartItemRepository.save(cartItem);

        log.info("Quantity updated for cart item with id: {}", cartItemId);
        return shoppingCartMapper
                .toDto(cartItem.getShoppingCart());
    }

    @Transactional
    @Override
    public void removeProductFromShoppingCart(Authentication authentication, Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> {
                    log.error("Cart item not found with id: {}", cartItemId);
                    return new EntityNotFoundException("Can't get cart item. Id: " + cartItemId);
                });
        ShoppingCart shoppingCart = cartItem.getShoppingCart();
        shoppingCart.removeItemFromCart(cartItem);
        cartItemRepository.delete(cartItem);
        shoppingCartRepository.save(shoppingCart);

        log.info("Product removed successfully "
                + "from shopping cart for cart item id: {}", cartItemId);
    }
}
