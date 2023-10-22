package ru.solarev.core.coreservice.converter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.solarev.core.coreservice.dto.ProductDto;
import ru.solarev.core.coreservice.model.Product;

@Getter
@Setter
@Component
public class ProductConverter {

    public ProductDto entityToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .description(product.getDescription())
                .pathname(product.getPathname())
                .build();
    }

    public Product dtoToEntity(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .title(productDto.getTitle())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .pathname(productDto.getPathname())
                .build();
    }
}
