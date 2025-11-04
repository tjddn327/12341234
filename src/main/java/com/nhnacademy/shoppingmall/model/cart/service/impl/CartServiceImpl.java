package com.nhnacademy.shoppingmall.model.cart.service.impl;

import com.nhnacademy.shoppingmall.model.cart.domain.Cart;
import com.nhnacademy.shoppingmall.model.cart.domain.CartPk;
import com.nhnacademy.shoppingmall.model.cart.repository.JpaCartRepository;
import com.nhnacademy.shoppingmall.model.cart.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service // [추가]
public class CartServiceImpl implements CartService {

    private final JpaCartRepository jpaCartRepository;

    @Autowired // [추가]
    public CartServiceImpl(JpaCartRepository jpaCartRepository) {
        this.jpaCartRepository = jpaCartRepository;
    }

    @Override
    @Transactional // [추가]
    public void addCartItem(Cart cart) {
        // [수정] ON DUPLICATE KEY UPDATE 로직 구현
        CartPk pk = new CartPk(cart.getUser().getUserId(), cart.getProduct().getProductId());
        Optional<Cart> existingCartItem = jpaCartRepository.findById(pk);

        if (existingCartItem.isPresent()) {
            Cart item = existingCartItem.get();
            item.setQuantity(item.getQuantity() + cart.getQuantity());
            jpaCartRepository.save(item);
            log.info("Updated quantity for cart item: user={}, product={}", pk.getUserId(), pk.getProductId());
        } else {
            cart.setPk(pk);
            cart.setCreatedAt(LocalDateTime.now());
            jpaCartRepository.save(cart);
            log.info("Added new cart item: user={}, product={}", pk.getUserId(), pk.getProductId());
        }
    }

    @Override
    @Transactional // [추가]
    public void deleteCartItem(String userId, int productId) { // [수정] int로 변경
        if (!jpaCartRepository.existsByPkUserIdAndPkProductId(userId, productId)) {
            log.warn("Cart item not found or failed to delete for user: {}, product: {}", userId, productId);
            return;
        }
        jpaCartRepository.deleteByPkUserIdAndPkProductId(userId, productId);
    }

    @Override
    @Transactional(readOnly = true) // [추가]
    public List<Cart> getCartItems(String userId) {
        return jpaCartRepository.findByPkUserId(userId);
    }

    @Override
    @Transactional
    public void clearCart(String userId) {
        jpaCartRepository.deleteAllByPkUserId(userId);
    }
}