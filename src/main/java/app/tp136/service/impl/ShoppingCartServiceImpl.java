package app.tp136.service.impl;

import app.tp136.dto.request.AddProductToCartRequestDto;
import app.tp136.dto.request.UpdateQuantityProductRequestDto;
import app.tp136.dto.responce.ShoppingCartResponseDto;
import app.tp136.exception.EntityNotFoundException;
import app.tp136.mapper.ShoppingCartMapper;
import app.tp136.model.CartItem;
import app.tp136.model.ShoppingCart;
import app.tp136.model.User;
import app.tp136.repository.CartItemRepository;
import app.tp136.repository.ProductRepository;
import app.tp136.repository.ShoppingCartRepository;
import app.tp136.security.CustomUserDetailsService;
import app.tp136.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    }

    @Transactional
    @Override
    public ShoppingCartResponseDto getShoppingCartById(Authentication authentication) {
        Long userId = customUserDetailsService.getUserIdFromAuthentication(authentication);
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException(
                        "Can`t find shopping cart for this use. Id: " + userId));
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Transactional
    @Override
    public ShoppingCartResponseDto addProductToShoppingCart(Authentication authentication,
                                                            AddProductToCartRequestDto dto) {
        Long userId = customUserDetailsService.getUserIdFromAuthentication(authentication);
        Long productId = dto.getProductId();
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartById(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can`t find shopping cart for this use. Id: " + userId));
        boolean bookExists = shoppingCart.getCartItems().stream()
                .anyMatch(cartItem -> cartItem.getProduct().getId().equals(productId));
        if (bookExists) {
            throw new IllegalArgumentException("Book already exists. Id: " + productId);
        }
        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(shoppingCart);
        cartItem.setQuantity(dto.getQuantity());
        cartItem.setProduct(productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Can`t find book. Id: "
                        + productId)));
        shoppingCart.getCartItems().add(cartItem);
        cartItemRepository.save(cartItem);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Transactional
    @Override
    public ShoppingCartResponseDto updateQuantityProduct(Authentication authentication,
                                                         Long cartItemId,
                                                         UpdateQuantityProductRequestDto dto) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can`t get cart. Id: " + cartItemId));
        cartItem.setQuantity(dto.quantity());
        cartItemRepository.save(cartItem);
        return shoppingCartMapper
                .toDto(cartItem.getShoppingCart());
    }

    @Transactional
    @Override
    public void removeProductFromShoppingCart(Authentication authentication, Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't get cart item. Id: " + cartItemId));
        ShoppingCart shoppingCart = cartItem.getShoppingCart();
        shoppingCart.removeItemFromCart(cartItem);
        cartItemRepository.delete(cartItem);
        shoppingCartRepository.save(shoppingCart);
    }
}
