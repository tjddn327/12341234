package com.nhnacademy.shoppingmall.model.product.service;

import com.nhnacademy.shoppingmall.model.product.domain.Product;
import java.util.List;

public interface ProductService {
    Product getProduct(int productId);
    List<Product> getAllProducts();
    List<Product> getProductsPage(int offset, int limit);
    int getProductCount();
    void saveProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int productId);
}
