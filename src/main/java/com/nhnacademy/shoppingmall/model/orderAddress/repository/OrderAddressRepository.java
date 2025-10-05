package com.nhnacademy.shoppingmall.model.orderAddress.repository;

import com.nhnacademy.shoppingmall.model.orderAddress.domain.OrderAddress;

import java.util.Optional;

public interface OrderAddressRepository {
    int save(OrderAddress orderAddress);
    Optional<OrderAddress> findByOrderId(int orderId);
}