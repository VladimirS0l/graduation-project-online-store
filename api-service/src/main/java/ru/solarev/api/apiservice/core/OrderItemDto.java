package ru.solarev.api.apiservice.core;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OrderItemDto {
    private Long productId;
    private String productTitle;
    private Integer quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;

    @Override
    public String toString() {
        return productTitle +
                " - " + quantity + " шт.";
    }
}
