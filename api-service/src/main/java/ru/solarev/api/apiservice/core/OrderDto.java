package ru.solarev.api.apiservice.core;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.solarev.api.apiservice.core.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Builder
public class OrderDto {
    @Schema(description = "Идентификатор заказа", example = "1")
    private Long id;

    @Schema(description = "Ник получателя", example = "user")
    private String username;

    @Schema(description = "Имя получателя", example = "Иванов Иван Иванович")
    private String fullName;

    @Schema(description = "Список покупок", example = "{Товар#1 - 1 шт., Товар#2 - 2 шт.}")
    private Set<OrderItemDto> items;

    @Schema(description = "Стоимость заказа", example = "100.00")
    private BigDecimal totalPrice;

    @Schema(description = "Адрес доставки", example = "614000, г. Пермь, ул. Мира, д.1, кв.1")
    private String address;

    @Schema(description = "Телефон получателя", example = "+7(900)-800-00-00")
    private String phone;

    @Schema(description = "Статус заказа", example = "CREATED")
    private OrderStatus orderStatus;
}
