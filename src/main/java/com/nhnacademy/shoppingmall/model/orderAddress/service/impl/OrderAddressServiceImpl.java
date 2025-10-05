package com.nhnacademy.shoppingmall.model.orderAddress.service.impl;

import com.nhnacademy.shoppingmall.model.orderAddress.domain.OrderAddress;
import com.nhnacademy.shoppingmall.model.orderAddress.repository.OrderAddressRepository;
import com.nhnacademy.shoppingmall.model.orderAddress.service.OrderAddressService;

import java.util.Optional;

public class OrderAddressServiceImpl implements OrderAddressService {
    private final OrderAddressRepository orderAddressRepository;

    public OrderAddressServiceImpl(OrderAddressRepository orderAddressRepository) {
        this.orderAddressRepository = orderAddressRepository;
    }

    @Override
    public void saveOrderAddress(OrderAddress orderAddress) {
        orderAddressRepository.save(orderAddress);
    }

    @Override
    public Optional<OrderAddress> getOrderAddress(int orderId) {
        return orderAddressRepository.findByOrderId(orderId);
    }
}
