package app.tp136.mapper;

import app.tp136.config.MapperConfig;
import app.tp136.dto.responce.ShoppingCartResponseDto;
import app.tp136.model.ShoppingCart;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, uses = CartItemMapper.class)
public interface ShoppingCartMapper {
    ShoppingCartResponseDto toDto(ShoppingCart shoppingCart);

    @AfterMapping
    default void setUserEmail(@MappingTarget ShoppingCartResponseDto dto,
                              ShoppingCart shoppingCart) {
        dto.setUserEmail(shoppingCart.getUser().getEmail());
    }
}
