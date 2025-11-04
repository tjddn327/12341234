package com.nhnacademy.shoppingmall.model.orderDetails.repository;

import com.nhnacademy.shoppingmall.model.orderDetails.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JpaOrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    // OrderDetail 엔티티의 'order' 필드 내부의 'orderId' 필드를 찾음
    List<OrderDetail> findByOrder_OrderId(int orderId);
}