package app.tp136.mapper;

import app.tp136.config.MapperConfig;
import app.tp136.dto.responce.CartItemResponseDto;
import app.tp136.model.CartItem;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.nameUa", target = "productNameUa")
    @Mapping(source = "product.nameEng", target = "productNameEng")
    CartItemResponseDto toDto(CartItem cartItem);

    Set<CartItemResponseDto> toDtoSet(Set<CartItem> cartItems);
}
