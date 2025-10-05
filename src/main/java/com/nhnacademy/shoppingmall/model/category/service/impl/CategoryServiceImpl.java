package com.nhnacademy.shoppingmall.model.category.service.impl;

import com.nhnacademy.shoppingmall.model.category.domain.Category;
import com.nhnacademy.shoppingmall.model.category.exception.CategoryNotFoundException;
import com.nhnacademy.shoppingmall.model.category.repository.CategoryRepository;
import com.nhnacademy.shoppingmall.model.category.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category getCategory(int categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Category category) {
        if (categoryRepository.findById(category.getCategoryId()).isEmpty()) {
            throw new CategoryNotFoundException(category.getCategoryId());
        }
        categoryRepository.update(category);
    }


    @Override
    public void deleteCategory(int categoryId) {
        if (categoryRepository.findById(categoryId).isEmpty()) {
            throw new CategoryNotFoundException(categoryId);
        }
        categoryRepository.deleteById(categoryId);

    }
}
