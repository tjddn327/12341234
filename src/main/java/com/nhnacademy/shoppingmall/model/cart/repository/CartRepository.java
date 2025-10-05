package com.nhnacademy.shoppingmall.model.cart.repository;

import com.nhnacademy.shoppingmall.model.cart.domain.Cart;

import java.util.List;

public interface CartRepository {
    int save(Cart cart);
    int deleteByUserIdAndProductId(String userId, int productId);
    List<Cart> findByUserId(String userId);
    int countByUserIdAndProductId(String userId, int productId);
}
