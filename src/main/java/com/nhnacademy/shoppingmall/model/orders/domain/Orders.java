package com.nhnacademy.shoppingmall.model.orders.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    private int orderId;
    private String userId;
    private LocalDateTime orderDate;
    private String shipAddress;
    private int totalPrice;
}