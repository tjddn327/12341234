package com.nhnacademy.shoppingmall.model.cart.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private String userId;
    private int productId;
    private int quantity;
}
