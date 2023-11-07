package ru.solarev.api.apiservice.core;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OrderItemDto {
    @Schema(description = "Идентификатор элемента товара", example = "1")
    private Long productId;

    @Schema(description = "Название товара", example = "Товар#1")
    private String productTitle;

    @Schema(description = "Количество товара", example = "2")
    private Integer quantity;

    @Schema(description = "Цена за единицу", example = "50")
    private BigDecimal pricePerProduct;

    @Schema(description = "Стоимость элемента заказа", example = "100")
    private BigDecimal price;

    @Override
    public String toString() {
        return productTitle +
                " - " + quantity + " шт.";
    }
}
