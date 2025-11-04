package com.nhnacademy.shoppingmall.model.cart.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class CartPk implements Serializable {

    @Column(name = "UserID", length = 50)
    private String userId;

    @Column(name = "ProductID")
    private int productId;
}