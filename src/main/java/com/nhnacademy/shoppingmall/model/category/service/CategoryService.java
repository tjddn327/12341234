package com.nhnacademy.shoppingmall.model.category.service;

import com.nhnacademy.shoppingmall.model.category.domain.Category;

import java.util.List;

public interface CategoryService {
    Category getCategory(int categoryId);
    List<Category> getAllCategories();
    void saveCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(int categoryId);
}
