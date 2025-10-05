package com.nhnacademy.shoppingmall.model.category.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(int categoryId) {
        super("Category not found: " + categoryId);
    }
}
