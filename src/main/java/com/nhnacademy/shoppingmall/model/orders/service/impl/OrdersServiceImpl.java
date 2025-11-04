package com.nhnacademy.shoppingmall.model.orders.service.impl;

import com.nhnacademy.shoppingmall.model.orders.domain.Orders;
import com.nhnacademy.shoppingmall.model.orders.exception.OrderNotFoundException;
import com.nhnacademy.shoppingmall.model.orders.repository.JpaOrdersRepository;
import com.nhnacademy.shoppingmall.model.orders.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // [추가]
public class OrdersServiceImpl implements OrdersService {

    private final JpaOrdersRepository jpaOrdersRepository;

    @Autowired // [추가]
    public OrdersServiceImpl(JpaOrdersRepository jpaOrdersRepository) {
        this.jpaOrdersRepository = jpaOrdersRepository;
    }

    @Override
    @Transactional(readOnly = true) // [추가]
    public Orders getOrder(int orderId) {
        return jpaOrdersRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    @Override
    @Transactional // [추가]
    public Orders saveOrder(Orders order) { // [수정] 저장된 Orders 객체 반환
        return jpaOrdersRepository.save(order);
    }

    @Override
    @Transactional // [추가]
    public void updateOrder(Orders order) {
        if(!jpaOrdersRepository.existsById(order.getOrderId())){
            throw new OrderNotFoundException(order.getOrderId());
        }
        jpaOrdersRepository.save(order);
    }

    @Override
    @Transactional // [추가]
    public void deleteOrder(int orderId) {
        if(!jpaOrdersRepository.existsById(orderId)){
            throw new OrderNotFoundException(orderId);
        }
        jpaOrdersRepository.deleteById(orderId);
    }

    @Override
    @Transactional(readOnly = true) // [추가]
    public List<Orders> findByUserId(String userId) {
        return jpaOrdersRepository.findByUser_UserIdOrderByOrderDateDesc(userId);
    }

    @Override
    @Transactional(readOnly = true) // [추가]
    public List<Orders> getAllOrders() {
        return jpaOrdersRepository.findAllByOrderByOrderDateDesc();
    }
}