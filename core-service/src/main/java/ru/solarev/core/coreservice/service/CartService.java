package ru.solarev.core.coreservice.service;

import ru.solarev.api.apiservice.card.CartDto;
import ru.solarev.core.coreservice.model.Cart;

public interface CartService {
    String generateCartUuid(String username);
    Cart getCurrentCart(String cartKey);
    String getCartUuidFromSuffix(String suffix);
    public CartDto getCurrentCartDtoByUsername(String username);
    void addToCart(String cartKey, Long productId);
    void clearCart(String cartKey);
    void removeItemFromCart(String cartKey, Long productId);
    void decrementItem(String cartKey, Long productId);
    void merge(String userCartKey, String guestCartKey);
    void updateCart(String cartKey, Cart cart);
}
