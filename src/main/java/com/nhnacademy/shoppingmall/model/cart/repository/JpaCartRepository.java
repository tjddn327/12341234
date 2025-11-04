package com.nhnacademy.shoppingmall.model.cart.repository;

import com.nhnacademy.shoppingmall.model.cart.domain.Cart;
import com.nhnacademy.shoppingmall.model.cart.domain.CartPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface JpaCartRepository extends JpaRepository<Cart, CartPk> {

    // Cart 엔티티의 pk 필드 내부의 userId 필드를 찾음
    List<Cart> findByPkUserId(String userId);

    // 복합키(pk) 필드명(userId, productId)을 따라 PkUserIdAndPkProductId로 작성
    @Transactional
    void deleteByPkUserIdAndPkProductId(String userId, int productId);
    
    @Transactional
    void deleteAllByPkUserId(String userId);

    boolean existsByPkUserIdAndPkProductId(String userId, int productId);
}