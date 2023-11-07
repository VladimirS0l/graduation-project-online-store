package ru.solarev.core.coreservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.solarev.api.apiservice.card.CartDto;
import ru.solarev.api.apiservice.core.ProductDto;
import ru.solarev.core.coreservice.converter.CartMapper;
import ru.solarev.core.coreservice.converter.ProductMapper;
import ru.solarev.core.coreservice.model.Cart;
import ru.solarev.core.coreservice.service.CartService;
import ru.solarev.core.coreservice.service.ProductService;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private String currentCardKey = "";

    private final String cartPrefix = "cardUuid";

    private final ProductService productService;
    private final ProductMapper mapper;
    private final CartMapper cartMapper;

    Map<String, Cart> cartStorage = new HashMap<>();

    public String getCartUuidFromSuffix(String suffix) {
        return cartPrefix + suffix;
    }

    public String generateCartUuid(String username) {
        if (username != null) setCurrentCardKey(cartPrefix + username);
        else setCurrentCardKey(UUID.randomUUID().toString());
        return currentCardKey;
    }

    public Cart getCurrentCart(String cartKey) {
        return cartStorage.get(cartKey);
    }

    public CartDto getCurrentCartDtoByUsername(String username) {
        return cartMapper.toDto(getCurrentCart(cartPrefix + username));
    }

    public void addToCart(String cartKey, Long productId) {
        ProductDto productDto = mapper.toDto(productService.getProductById(productId));
        execute(cartKey, c -> c.add(productDto));
    }

    public void clearCart(String cartKey) {
        execute(cartPrefix+cartKey, Cart::clear);
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

    public String getCurrentCardKey() {
        return currentCardKey;
    }

    public void setCurrentCardKey(String currentCardKey) {
        this.currentCardKey = currentCardKey;
    }
}
