package ru.solarev.core.coreservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.solarev.api.apiservice.card.CartDto;
import ru.solarev.api.apiservice.card.response.CartResponse;
import ru.solarev.core.coreservice.converter.CartMapper;
import ru.solarev.core.coreservice.service.CartService;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
@Tag(name = "Корзина", description = "Методы работы с корзиной")
public class CartController {
    private final CartService cartService;
    private final CartMapper cartMapper;

    @Operation(summary = "Запрос на получение корзины " +
            "по идентификационному номеру пользователя")
    @GetMapping("/{uuid}")
    public CartDto getCurrentCart(
            @RequestHeader(required = false) String username,
            @PathVariable String uuid) {
        return cartMapper.toDto(cartService
                .getCurrentCart(getCurrentCartUuid(username, uuid)));
    }

    @Operation(summary = "Генерация UUID корзины")
    @GetMapping("/generate")
    public CartResponse getCartUuid(@RequestHeader(required = false) String username) {
        return new CartResponse(cartService.generateCartUuid(username));
    }

    @Operation(summary = "Добавление продукта в корзину")
    @GetMapping("/{uuid}/add/{productId}")
    public void add(@RequestHeader(required = false) String username,
                    @PathVariable String uuid,
                    @PathVariable Long productId) {
        cartService.addToCart(getCurrentCartUuid(username, uuid), productId);
    }

    @Operation(summary = "Уменьшение количества продукта в корзине")
    @GetMapping("/{uuid}/decrement/{productId}")
    public void decrement(@RequestHeader(required = false) String username,
                          @PathVariable String uuid,
                          @PathVariable Long productId) {
        cartService.decrementItem(getCurrentCartUuid(username, uuid), productId);
    }

    @Operation(summary = "Удаление продукта из корзины")
    @GetMapping("/{uuid}/remove/{productId}")
    public void remove(@RequestHeader(required = false) String username,
                       @PathVariable String uuid,
                       @PathVariable Long productId) {
        cartService.removeItemFromCart(getCurrentCartUuid(username, uuid), productId);
    }

    @Operation(summary = "Очистить корзину")
    @GetMapping("/{uuid}/clear")
    public void clear(@RequestHeader(required = false) String username,
                      @PathVariable String uuid) {
        cartService.clearCart(getCurrentCartUuid(username, uuid));
    }

    @Operation(summary = "Слияние корзин не авторизованного и авторизованного пользователя")
    @GetMapping("/{uuid}/merge")
    public void merge(@RequestHeader(required = false) String username,
                      @PathVariable String uuid) {
        cartService.merge(
                getCurrentCartUuid(username, null),
                getCurrentCartUuid(null, uuid)
        );
    }

    private String getCurrentCartUuid(String username, String uuid) {
        if (username != null) {
            return cartService.getCartUuidFromSuffix(username);
        }
        return cartService.getCartUuidFromSuffix(uuid);
    }
}
