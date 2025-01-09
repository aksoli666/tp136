package app.tp136.mapper;

import app.tp136.config.MapperConfig;
import app.tp136.dto.OrderItemDto;
import app.tp136.model.OrderItem;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    @Mapping(source = "product.id", target = "productId")
    OrderItemDto toDto(OrderItem cartItem);

    Set<OrderItemDto> toDtos(Set<OrderItem> cartItems);
}
