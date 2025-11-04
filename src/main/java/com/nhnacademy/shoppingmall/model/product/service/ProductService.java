package com.nhnacademy.shoppingmall.model.product.service;

import com.nhnacademy.shoppingmall.model.product.domain.Product;
import java.util.List;

public interface ProductService {
    Product getProduct(int productId); // [수정] 파라미터 타입 int로 변경
    List<Product> getAllProducts();
    List<Product> getProductsPage(int offset, int limit);
    int getProductCount();
    void saveProduct(Product product); // DTO 대신 엔티티 사용
    void updateProduct(Product product); // DTO 대신 엔티티 사용
    void deleteProduct(int productId);
}