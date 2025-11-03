package com.nhnacademy.springbootjpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

// TODO #2: `OrderItem` Entity 클래스의 복합키 클래스인 `OrderItemPk` 클래스를 작성하세요.
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OrderItemPk {
    @Column(name = "order_id")
    private Long orderId;

    @Getter
    @Column(name = "line_number")
    private Integer lineNumber;

    public long getOrderId() {
        return orderId;
    }

}
