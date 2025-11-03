package com.nhnacademy.springbootjpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

// TODO #1: `order_item` 테이블과 매핑될 `OrderItem` Entity 클래스를 작성하세요. foreign key 부분은 무시합니다.
/*
 * create table order_item
 * (
 *     order_id    bigint not null,
 *     line_number int    not null,
 *     item_id     bigint not null,
 *     quantity    int    not null,
 *     primary key (order_id, line_number),
 *     constraint order_item_item_id_fk
 *         foreign key (item_id) references item (id),
 *     constraint order_item_order_id_fk
 *         foreign key (order_id) references "order" (id)
 * );
 */
@Entity
public class OrderItem {
    @Getter
    @EmbeddedId
    private OrderItemPk pk;

    @Getter
    @Setter
    @NotNull
    private long itemId;

    @Getter
    @Setter
    @NotNull
    private int quantity;

}
