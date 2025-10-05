package com.nhnacademy.shoppingmall.model.orders.repository;

import com.nhnacademy.shoppingmall.model.orders.domain.Orders;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository {
    Optional<Orders> findById(int orderId);
    int save(Orders order);
    int deleteByOrderId(int orderId);
    int update(Orders order);
    List<Orders> findByUserId(String userId);
    List<Orders> findAll();
}
