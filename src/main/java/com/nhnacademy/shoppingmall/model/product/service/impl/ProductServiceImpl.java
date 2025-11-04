package com.nhnacademy.shoppingmall.model.product.service.impl;

import com.nhnacademy.shoppingmall.model.category.domain.Category;
import com.nhnacademy.shoppingmall.model.category.repository.JpaCategoryRepository;
import com.nhnacademy.shoppingmall.model.product.domain.Product;
import com.nhnacademy.shoppingmall.model.product.exception.ProductNotFoundException;
import com.nhnacademy.shoppingmall.model.product.repository.JpaProductRepository;
import com.nhnacademy.shoppingmall.model.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // [추가]
public class ProductServiceImpl implements ProductService {

    private final JpaProductRepository jpaProductRepository;
    // CategoryId로 Category를 찾아야 하므로 Category 리포지토리도 주입
    private final JpaCategoryRepository jpaCategoryRepository;

    @Autowired // [추가]
    public ProductServiceImpl(JpaProductRepository jpaProductRepository, JpaCategoryRepository jpaCategoryRepository) {
        this.jpaProductRepository = jpaProductRepository;
        this.jpaCategoryRepository = jpaCategoryRepository;
    }

    @Override
    @Transactional(readOnly = true) // [추가]
    public Product getProduct(int productId) { // [수정] 파라미터 타입 int로 변경
        return jpaProductRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    @Override
    @Transactional(readOnly = true) // [추가]
    public List<Product> getAllProducts() {
        return jpaProductRepository.findAllByOrderByProductIdDesc();
    }

    @Override
    @Transactional(readOnly = true) // [추가]
    public List<Product> getProductsPage(int offset, int limit) {
        int pageNumber = offset / limit;
        Page<Product> page = jpaProductRepository.findAllByOrderByProductIdDesc(PageRequest.of(pageNumber, limit));
        return page.getContent();
    }

    @Override
    @Transactional(readOnly = true) // [추가]
    public int getProductCount() {
        return (int) jpaProductRepository.count();
    }

    // [수정] Product DTO 대신 실제 엔티티를 받도록 수정 (컨트롤러에서 변환 필요)
    @Override
    @Transactional // [추가]
    public void saveProduct(Product product) {
        // 컨트롤러에서 product.setCategory()를 미리 설정해줘야 함
        jpaProductRepository.save(product);
    }

    @Override
    @Transactional // [추가]
    public void updateProduct(Product product) {
        if (!jpaProductRepository.existsById(product.getProductId())) {
            throw new ProductNotFoundException(product.getProductId());
        }
        // 컨트롤러에서 product.setCategory()를 미리 설정해줘야 함
        jpaProductRepository.save(product);
    }

    @Override
    @Transactional // [추가]
    public void deleteProduct(int productId) {
        if (!jpaProductRepository.existsById(productId)) {
            throw new ProductNotFoundException(productId);
        }
        jpaProductRepository.deleteById(productId);
    }
}