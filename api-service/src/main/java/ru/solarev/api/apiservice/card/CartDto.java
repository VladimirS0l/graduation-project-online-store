package ru.solarev.api.apiservice.card;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Модель корзины")
public class CartDto {
    @Schema(description = "Товары в корзине", example = "{1, Товар№1, 2, 50.00, 100.00}")
    private List<CartItemDto> items;

    @Schema(description = "Стоимость корзины",  example = "100.00")
    private BigDecimal totalPrice;
}