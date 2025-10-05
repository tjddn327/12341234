package com.nhnacademy.shoppingmall.model.cart.service;

import com.nhnacademy.shoppingmall.model.cart.domain.Cart;

import java.util.List;

public interface CartService {
    void addCartItem(Cart cart);
    void deleteCartItem(String userId, int productId);
    List<Cart> getCartItems(String userId);
}
