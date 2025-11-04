package com.nhnacademy.shoppingmall.model.orderAddress.domain;

import com.nhnacademy.shoppingmall.model.orders.domain.Orders;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OrderAddresses")
public class OrderAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AddressID")
    private int addressId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderID", nullable = false)
    private Orders order;

    @Column(name = "ZipCode", nullable = false, length = 20)
    private String zipCode;

    @Column(name = "Address", nullable = false)
    private String address;

    @Column(name = "AddressDetail")
    private String addressDetail;
}