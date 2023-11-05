package ru.solarev.core.coreservice.converter;

import org.mapstruct.Mapper;
import ru.solarev.api.apiservice.core.OrderDto;
import ru.solarev.core.coreservice.model.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper extends Mappable<Order, OrderDto> {
}
