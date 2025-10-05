package com.nhnacademy.shoppingmall.model.category.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
    private int categoryId;
    private String categoryName;
}
