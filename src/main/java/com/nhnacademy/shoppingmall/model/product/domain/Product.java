package com.nhnacademy.shoppingmall.model.product.domain;

import com.nhnacademy.shoppingmall.model.category.domain.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private int productId;

    @Column(name = "ModelNumber", nullable = false, unique = true, length = 50)
    private String modelNumber;

    @Column(name = "ModelName", nullable = false, length = 100)
    private String modelName;

    @Column(name = "ProductImage")
    private String productImage;

    @Column(name = "UnitPrice", nullable = false)
    private int unitPrice;

    @Lob
    @Column(name = "Description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryID")
    private Category category;

    public Product(int productId, String modelNumber, String modelName, String productImage, int unitPrice, String description, Category category) {
        this.productId = productId;
        this.modelNumber = modelNumber;
        this.modelName = modelName;
        this.productImage = productImage;
        this.unitPrice = unitPrice;
        this.description = description;
        this.category = category;
    }
}