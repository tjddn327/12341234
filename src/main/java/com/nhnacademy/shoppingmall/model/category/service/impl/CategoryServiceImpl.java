package com.nhnacademy.shoppingmall.model.category.service.impl;

import com.nhnacademy.shoppingmall.model.category.domain.Category;
import com.nhnacademy.shoppingmall.model.category.exception.CategoryNotFoundException;
import com.nhnacademy.shoppingmall.model.category.repository.JpaCategoryRepository;
import com.nhnacademy.shoppingmall.model.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // [추가]
public class CategoryServiceImpl implements CategoryService {

    private final JpaCategoryRepository jpaCategoryRepository;

    @Autowired // [추가]
    public CategoryServiceImpl(JpaCategoryRepository jpaCategoryRepository) {
        this.jpaCategoryRepository = jpaCategoryRepository;
    }

    @Override
    @Transactional(readOnly = true) // [추가]
    public Category getCategory(int categoryId) {
        return jpaCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }

    @Override
    @Transactional(readOnly = true) // [추가]
    public List<Category> getAllCategories() {
        return jpaCategoryRepository.findAll();
    }

    @Override
    @Transactional // [추가]
    public void saveCategory(Category category) {
        jpaCategoryRepository.save(category);
    }

    @Override
    @Transactional // [추가]
    public void updateCategory(Category category) {
        if (!jpaCategoryRepository.existsById(category.getCategoryId())) {
            throw new CategoryNotFoundException(category.getCategoryId());
        }
        jpaCategoryRepository.save(category); // save가 update 겸함
    }

    @Override
    @Transactional // [추가]
    public void deleteCategory(int categoryId) {
        if (!jpaCategoryRepository.existsById(categoryId)) {
            throw new CategoryNotFoundException(categoryId);
        }
        jpaCategoryRepository.deleteById(categoryId);
    }
}