package ru.solarev.core.coreservice.productTest;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.solarev.api.apiservice.core.ProductDto;
import ru.solarev.core.coreservice.converter.ProductMapper;
import ru.solarev.core.coreservice.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
public class TestProductMapper {

    @Autowired
    private ProductMapper mapper;


    @Test
    public void testProductMapper() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Iphone");
        product.setDescription("Lorem asdasdnjhasdkasd asdn ashd gasbd j");
        product.setPrice(BigDecimal.valueOf(323211.11));
        product.setPathname("/src/test");
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        //when
        ProductDto dto = mapper.toDto(product);
        System.out.println(dto.getId() + ", " + dto.getTitle()+ ", " +
                dto.getDescription() + ", " + dto.getPrice() + ", " + dto.getPathname());

        //then
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(product.getId(), dto.getId());
        Assertions.assertEquals(product.getTitle(), dto.getTitle());
    }
}
