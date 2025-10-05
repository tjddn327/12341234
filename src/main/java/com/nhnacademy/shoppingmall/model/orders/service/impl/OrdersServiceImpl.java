package com.nhnacademy.shoppingmall.model.orders.service.impl;

import com.nhnacademy.shoppingmall.model.orders.domain.Orders;
import com.nhnacademy.shoppingmall.model.orders.exception.OrderNotFoundException;
import com.nhnacademy.shoppingmall.model.orders.repository.OrdersRepository;
import com.nhnacademy.shoppingmall.model.orders.service.OrdersService;

import java.util.List;

public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;

    public OrdersServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Orders getOrder(int orderId) {
        return ordersRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    @Override
    public void saveOrder(Orders order) {
        ordersRepository.save(order);
    }

    @Override
    public void updateOrder(Orders order) {
        if(ordersRepository.findById(order.getOrderId()).isEmpty()){
            throw new OrderNotFoundException(order.getOrderId());
        }
        ordersRepository.update(order);
    }

    @Override
    public void deleteOrder(int orderId) {
        if(ordersRepository.findById(orderId).isEmpty()){
            throw new OrderNotFoundException(orderId);
        }
        ordersRepository.deleteByOrderId(orderId);
    }

    @Override
    public List<Orders> findByUserId(String userId) {
        return ordersRepository.findByUserId(userId);
    }

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }
}

