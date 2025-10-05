package com.nhnacademy.shoppingmall.model.product.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(int productId) {
        super("Product not found: " + productId);
    }
}