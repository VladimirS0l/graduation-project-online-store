package ru.solarev.core.coreservice.service.impl;

import lombok.RequiredArgsConstructor;
import ru.solarev.api.apiservice.core.ProductDto;
import ru.solarev.core.coreservice.converter.ProductMapper;
import ru.solarev.core.coreservice.model.Cart;
import ru.solarev.core.coreservice.service.CartService;
import ru.solarev.core.coreservice.service.ProductService;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final String cartPrefix = "cardUuid";

    private final ProductService productService;
    private final ProductMapper mapper;

    Map<String, Cart> cartStorage = new HashMap<>();

    public String getCartUuidFromSuffix(String suffix) {
        return cartPrefix + suffix;
    }

    public String generateCartUuid() {
        return UUID.randomUUID().toString();
    }

    public Cart getCurrentCart(String cartKey) {
        return cartStorage.get(cartKey);
    }

    public void addToCart(String cartKey, Long productId) {
        ProductDto productDto = mapper.toDto(productService.getProductById(productId));
        execute(cartKey, c -> c.add(productDto));
    }

    public void clearCart(String cartKey) {
        execute(cartKey, Cart::clear);
    }

    public void removeItemFromCart(String cartKey, Long productId) {
        execute(cartKey, c -> c.remove(productId));
    }

    public void decrementItem(String cartKey, Long productId) {
        execute(cartKey, c -> c.decrement(productId));
    }

    public void merge(String userCartKey, String guestCartKey) {
        Cart guestCart = getCurrentCart(guestCartKey);
        Cart userCart = getCurrentCart(userCartKey);
        userCart.merge(guestCart);
        updateCart(guestCartKey, guestCart);
        updateCart(userCartKey, userCart);
    }

    public void updateCart(String cartKey, Cart cart) {
        cartStorage.put(cartKey, cart);
    }

    private void execute(String cartKey, Consumer<Cart> action) {
        Cart cart = getCurrentCart(cartKey);
        action.accept(cart);
        cartStorage.put(cartKey, cart);
    }
}
