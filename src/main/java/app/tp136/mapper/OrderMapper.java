package app.tp136.mapper;

import app.tp136.config.MapperConfig;
import app.tp136.dto.responce.OrderResponseDto;
import app.tp136.model.Order;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, uses = OrderItemMapper.class)
public interface OrderMapper {
    OrderResponseDto toDto(Order order);

    @AfterMapping
    default void setUserId(@MappingTarget OrderResponseDto dto, Order order) {
        dto.setUserId(order.getUser().getId());
    }
}
