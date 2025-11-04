package com.nhnacademy.shoppingmall.model.orderDetails.service.impl;

import com.nhnacademy.shoppingmall.model.orderDetails.domain.OrderDetail;
import com.nhnacademy.shoppingmall.model.orderDetails.repository.JpaOrderDetailRepository;
import com.nhnacademy.shoppingmall.model.orderDetails.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final JpaOrderDetailRepository jpaOrderDetailRepository;

    @Autowired
    public OrderDetailServiceImpl(JpaOrderDetailRepository jpaOrderDetailRepository) {
        this.jpaOrderDetailRepository = jpaOrderDetailRepository;
    }

    @Override
    @Transactional
    public void saveOrderDetail(OrderDetail orderDetail) {
        jpaOrderDetailRepository.save(orderDetail);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetail> getOrderDetails(int orderId) {
        return jpaOrderDetailRepository.findByOrder_OrderId(orderId);
    }
}