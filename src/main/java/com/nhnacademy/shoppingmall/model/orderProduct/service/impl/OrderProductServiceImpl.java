package com.nhnacademy.shoppingmall.model.orderProduct.service.impl;

import com.nhnacademy.shoppingmall.model.orderProduct.domain.OrderProduct;
import com.nhnacademy.shoppingmall.model.orderProduct.repository.OrderProductRepository;
import com.nhnacademy.shoppingmall.model.orderProduct.service.OrderProductService;

import java.util.List;

public class OrderProductServiceImpl implements OrderProductService {
    private final OrderProductRepository orderProductRepository;

    public OrderProductServiceImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public void saveOrderProduct(OrderProduct orderProduct) {
        orderProductRepository.save(orderProduct);
    }

    @Override
    public List<OrderProduct> getOrderProducts(int orderId) {
        return orderProductRepository.findByOrderId(orderId);
    }
}
