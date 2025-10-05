package com.nhnacademy.shoppingmall.model.cart.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemView {
    private int productId;
    private String productName;
    private int unitPrice;
    private int quantity;
    private int totalPrice;

}