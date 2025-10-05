package com.nhnacademy.shoppingmall.model.orderAddress.service;

import com.nhnacademy.shoppingmall.model.orderAddress.domain.OrderAddress;

import java.util.Optional;

public interface OrderAddressService {
    void saveOrderAddress(OrderAddress orderAddress);
    Optional<OrderAddress> getOrderAddress(int orderId);
}
