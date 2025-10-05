package com.nhnacademy.shoppingmall.model.ordersInfo;

import com.nhnacademy.shoppingmall.model.orders.domain.Orders;
import com.nhnacademy.shoppingmall.model.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderInfo {
    private Orders order;
    private List<ProductWithQuantity> products;

    @Data
    @AllArgsConstructor
    public static class ProductWithQuantity {
        private Product product;
        private int quantity;
    }
}