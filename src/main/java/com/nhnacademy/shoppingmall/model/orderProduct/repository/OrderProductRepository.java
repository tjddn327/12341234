package com.nhnacademy.shoppingmall.model.orderProduct.repository;

import com.nhnacademy.shoppingmall.model.orderProduct.domain.OrderProduct;

import java.util.List;

public interface OrderProductRepository {
    int save(OrderProduct orderProduct);
    List<OrderProduct> findByOrderId(int orderId);
}