package com.nhnacademy.shoppingmall.model.cart.service;

import com.nhnacademy.shoppingmall.model.cart.domain.Cart;
import java.util.List;

public interface CartService {
    void addCartItem(Cart cart);
    void deleteCartItem(String userId, int productId); // [수정] int로 변경
    List<Cart> getCartItems(String userId);
    void clearCart(String userId); // [추가] 주문 완료 시 장바구니 비우기
}