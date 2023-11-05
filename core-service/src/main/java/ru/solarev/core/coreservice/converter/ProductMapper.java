package ru.solarev.core.coreservice.converter;

import org.mapstruct.Mapper;
import ru.solarev.core.coreservice.dto.ProductDto;
import ru.solarev.core.coreservice.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper extends Mappable<Product, ProductDto> {
}
