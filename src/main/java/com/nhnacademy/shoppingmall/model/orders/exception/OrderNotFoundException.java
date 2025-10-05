package com.nhnacademy.shoppingmall.model.orders.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(int orderId) {
        super("Order not found: " + orderId);
    }
}