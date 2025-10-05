package com.nhnacademy.shoppingmall.model.orderProduct.service;

import com.nhnacademy.shoppingmall.model.orderProduct.domain.OrderProduct;

import java.util.List;

public interface OrderProductService {
    void saveOrderProduct(OrderProduct orderProduct);
    List<OrderProduct> getOrderProducts(int orderId);
}
