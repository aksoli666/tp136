package app.tp136.service;

import app.tp136.dto.request.AddProductToCartRequestDto;
import app.tp136.dto.request.UpdateQuantityProductRequestDto;
import app.tp136.dto.responce.ShoppingCartResponseDto;
import app.tp136.model.User;
import org.springframework.security.core.Authentication;

public interface ShoppingCartService {
    void createShoppingCart(User user);

    ShoppingCartResponseDto getShoppingCartById(Authentication authentication);

    ShoppingCartResponseDto addProductToShoppingCart(Authentication authentication,
                                                     AddProductToCartRequestDto dto);

    ShoppingCartResponseDto updateQuantityProduct(Authentication authentication,
                                                  Long cartItemId,
                                                  UpdateQuantityProductRequestDto dto);

    void removeProductFromShoppingCart(Authentication authentication, Long cartItemId);
}
