package ru.solarev.api.apiservice.core;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    @Schema(description = "Идентификатор товара", example = "1")
    private Long id;

    @NotBlank(message = "Поле названия товара не должно быть пустым")
    @Size(min = 5, message = "Название товара должно быть не короче 5 символов")
    @Schema(description = "Название товара", maxLength = 255,
            minLength = 3, example = "Товар#1")
    private String title;

    @NotBlank(message = "Описание товара не должно быть пустым")
    @Size(min = 5, message = "Описание товара должно быть не короче 5 символов")
    @Schema(description = "Описание товара", maxLength = 255,
            minLength = 3, example = "Описание характеристик товара")
    private String description;

    @NotBlank(message = "Поле пути к картинке товара не должно быть пустым")
    @Schema(description = "Путь к картинке товара", maxLength = 255,
            minLength = 3, example = "img/1.png")
    private String pathname;

    @NotNull(message = "Поле цены товара не должно быть пустым")
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 10, fraction = 2)
    @Schema(description = "Цена товара", example = "50.00")
    private BigDecimal price;
}
