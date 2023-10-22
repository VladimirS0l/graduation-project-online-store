package ru.solarev.core.coreservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OrderItemDto {
//    @Schema(description = "Идентификатор элемента заказа", required = true, example = "1")
    private Long productId;
//    @Schema(description = "Название товара", required = true, example = "Товар#1")
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
