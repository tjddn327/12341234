package com.nhnacademy.springbootjpa.entity;

import jakarta.persistence.*;
import lombok.Setter;

import java.time.ZonedDateTime;

// TODO #1: `order` 테이블과 매핑될 `Order` Entity 클래스를 작성하세요.
/*
 * create table "order"
 * (
 *     id         bigint auto_increment
 *         primary key,
 *     ordered_at datetime not null
 * );
 */
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ordered_at")
    @Setter
    private ZonedDateTime orderDate;

    public long getId() {
        return this.id;
    }

    public ZonedDateTime getOrderedAt() {
        return this.orderDate;
    }
}
