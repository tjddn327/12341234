package com.nhnacademy.shoppingmall.model.orders.domain;

import com.nhnacademy.shoppingmall.model.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID")
    private int orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(name = "OrderDate", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "ShipAddress", nullable = false)
    private String shipAddress;

    @Column(name = "TotalPrice", nullable = false)
    private int totalPrice;

    public Orders(User user, LocalDateTime orderDate, String shipAddress, int totalPrice) {
        this.user = user;
        this.orderDate = orderDate;
        this.shipAddress = shipAddress;
        this.totalPrice = totalPrice;
    }
}