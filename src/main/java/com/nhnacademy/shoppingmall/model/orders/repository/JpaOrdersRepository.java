package com.nhnacademy.shoppingmall.model.orders.repository;

import com.nhnacademy.shoppingmall.model.orders.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JpaOrdersRepository extends JpaRepository<Orders, Integer> {

    // Orders 엔티티의 'user' 필드 내부의 'userId' 필드를 찾음
    List<Orders> findByUser_UserIdOrderByOrderDateDesc(String userId);

    List<Orders> findAllByOrderByOrderDateDesc();
}