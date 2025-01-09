package app.tp136.controler;

import app.tp136.dto.request.AddProductToCartRequestDto;
import app.tp136.dto.request.UpdateQuantityProductRequestDto;
import app.tp136.dto.responce.ShoppingCartResponseDto;
import app.tp136.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping cart management", description = "Endpoints for managing carts")
@RestController
@RequestMapping(value = "/cart", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @Operation(
            summary = "Retrieve shopping cart details",
            description = "Fetches the details of the authenticated user's shopping cart."
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public ShoppingCartResponseDto getShoppingCartById(Authentication authentication) {
        return shoppingCartService.getShoppingCartById(authentication);
    }

    @Operation(
            summary = "Add a product to the shopping cart",
            description = "Adds a product to the user's shopping cart using the provided details."
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ShoppingCartResponseDto addProductToShoppingCart(
            Authentication authentication, @RequestBody @Valid AddProductToCartRequestDto dto) {
        return shoppingCartService.addProductToShoppingCart(authentication, dto);
    }

    @Operation(
            summary = "Update product quantity in shopping cart",
            description = "Updates the quantity of a specific product in the user's shopping cart."
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/items/{cartItemId}")
    public ShoppingCartResponseDto updateQuantityProduct(
            Authentication authentication,
            @PathVariable @Positive Long cartItemId,
            @RequestBody @Valid UpdateQuantityProductRequestDto dto) {
        return shoppingCartService.updateQuantityProduct(authentication, cartItemId, dto);
    }

    @Operation(
            summary = "Remove a product from the shopping cart",
            description = "Removes a specific product from the user's shopping cart by its item ID."
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/items/{cartItemId}")
    public void removeProductFromShoppingCart(
            Authentication authentication, @PathVariable @Positive Long cartItemId) {
        shoppingCartService.removeProductFromShoppingCart(authentication, cartItemId);
    }
}
