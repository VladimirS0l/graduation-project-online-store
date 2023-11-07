package ru.solarev.core.coreservice.converter;

import org.mapstruct.Mapper;
import ru.solarev.api.apiservice.card.CartDto;
import ru.solarev.core.coreservice.model.Cart;

@Mapper(componentModel = "spring")
public interface CartMapper extends Mappable<Cart, CartDto>{
    CartDto toDto(Cart entity);
}
