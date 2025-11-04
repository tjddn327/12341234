package com.nhnacademy.shoppingmall.model.product.repository;

import com.nhnacademy.shoppingmall.model.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface JpaProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByModelNumber(String modelNumber);

    List<Product> findAllByOrderByProductIdDesc();

    Page<Product> findAllByOrderByProductIdDesc(Pageable pageable);
}