package ru.solarev.core.coreservice.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.solarev.api.apiservice.core.ProductDto;
import ru.solarev.core.coreservice.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper extends Mappable<Product, ProductDto> {
}
