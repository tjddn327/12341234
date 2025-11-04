package com.nhnacademy.shoppingmall.model.orderDetails.service;

import com.nhnacademy.shoppingmall.model.orderDetails.domain.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    void saveOrderDetail(OrderDetail orderDetail);
    List<OrderDetail> getOrderDetails(int orderId);
}
