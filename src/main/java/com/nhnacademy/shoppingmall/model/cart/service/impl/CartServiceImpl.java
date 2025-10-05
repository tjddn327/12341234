package com.nhnacademy.shoppingmall.model.cart.service.impl;

import com.nhnacademy.shoppingmall.model.cart.domain.Cart;
import com.nhnacademy.shoppingmall.model.cart.repository.CartRepository;
import com.nhnacademy.shoppingmall.model.cart.repository.impl.CartRepositoryImpl;
import com.nhnacademy.shoppingmall.model.cart.service.CartService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository = new CartRepositoryImpl();

    @Override
    public void addCartItem(Cart cart) {
        int result = cartRepository.save(cart);
        if (result < 1) {
            log.error("Failed to add cart item for user: {}, product: {}", cart.getUserId(), cart.getProductId());
            throw new RuntimeException("Failed to add item to cart.");
        }
    }

    @Override
    public void deleteCartItem(String userId, int productId) {
        int result = cartRepository.deleteByUserIdAndProductId(userId, productId);
        if (result < 1) {
            log.warn("Cart item not found or failed to delete for user: {}, product: {}", userId, productId);
        }
    }

    @Override
    public List<Cart> getCartItems(String userId) {
        return cartRepository.findByUserId(userId);
    }
}