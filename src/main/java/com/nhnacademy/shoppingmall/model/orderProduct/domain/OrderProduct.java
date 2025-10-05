package com.nhnacademy.shoppingmall.model.orderProduct.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {
    private int orderId;
    private int productId;
    private int quantity;
    private int unitPrice;
}