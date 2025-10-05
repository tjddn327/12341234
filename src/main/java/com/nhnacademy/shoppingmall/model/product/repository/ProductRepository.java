package com.nhnacademy.shoppingmall.model.product.repository;

import com.nhnacademy.shoppingmall.model.product.domain.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(int productId);
    Optional<Product> findByModelNumber(String modelNumber);
    List<Product> findAllOrderByNewest();
    List<Product> findPageOrderByNewest(int offset, int limit);
    int countAll();
    int save(Product product);
    int update(Product product);
    int deleteById(int productId);
}
