package ru.solarev.api.apiservice.card;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Модель элемента корзины")
public class CartItemDto {
    @Schema(description = "Идентификатор товара в  корзине", example = "1")
    private Long productId;
    @Schema(description = "Название товара", example = "Товар№1")
    private String productTitle;
    @Schema(description = "Количество товаров", example = "2")
    private int quantity;
    @Schema(description = "Цена за еденицу", example = "50.00")
    private BigDecimal pricePerProduct;
    @Schema(description = "Стоимость товаров в корзине",  example = "100.00")
    private BigDecimal price;
}
