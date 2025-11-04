package com.nhnacademy.shoppingmall.model.category.repository;

import com.nhnacademy.shoppingmall.model.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCategoryRepository extends JpaRepository<Category, Integer> {

}