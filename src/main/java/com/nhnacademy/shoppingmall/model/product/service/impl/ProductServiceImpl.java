package com.nhnacademy.shoppingmall.model.product.service.impl;

import com.nhnacademy.shoppingmall.model.product.domain.Product;
import com.nhnacademy.shoppingmall.model.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.model.product.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProduct(int productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("product not found: " + productId));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllOrderByNewest();
    }

    @Override
    public List<Product> getProductsPage(int offset, int limit) {
        return productRepository.findPageOrderByNewest(offset, limit);
    }

    @Override
    public int getProductCount() {
        return productRepository.countAll();
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.update(product);
    }

    @Override
    public void deleteProduct(int productId) {
        productRepository.deleteById(productId);
    }
}
