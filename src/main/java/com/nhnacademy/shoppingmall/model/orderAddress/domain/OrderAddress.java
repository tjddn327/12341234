package com.nhnacademy.shoppingmall.model.orderAddress.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderAddress {
    private int addressId;
    private int orderId;
    private String zipCode;
    private String address;
    private String addressDetail;
}