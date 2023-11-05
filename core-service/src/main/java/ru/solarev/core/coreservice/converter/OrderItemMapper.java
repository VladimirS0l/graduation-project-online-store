package ru.solarev.core.coreservice.converter;

import org.mapstruct.Mapper;
import ru.solarev.core.coreservice.dto.OrderItemDto;
import ru.solarev.core.coreservice.model.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderItemMapper extends Mappable<OrderItem, OrderItemDto> {
}
