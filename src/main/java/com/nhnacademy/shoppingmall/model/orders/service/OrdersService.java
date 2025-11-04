package com.nhnacademy.shoppingmall.model.orders.service;

import com.nhnacademy.shoppingmall.model.orders.domain.Orders;
import java.util.List;

public interface OrdersService {
    Orders getOrder(int orderId);
    Orders saveOrder(Orders order); // [수정] 반환 타입 Orders로
    void updateOrder(Orders order);
    void deleteOrder(int orderId);
    List<Orders> findByUserId(String userId);
    List<Orders> getAllOrders();
}