package com.nhnacademy.shoppingmall.model.product.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int productId;
    private String modelNumber;
    private String modelName;
    private String productImage;
    private int unitPrice;
    private String description;
    private int categoryId;
}