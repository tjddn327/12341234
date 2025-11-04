package com.nhnacademy.shoppingmall.model.orderAddress.repository;

import com.nhnacademy.shoppingmall.model.orderAddress.domain.OrderAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface JpaOrderAddressRepository extends JpaRepository<OrderAddress, Integer> {

    Optional<OrderAddress> findByOrder_OrderId(int orderId);
}