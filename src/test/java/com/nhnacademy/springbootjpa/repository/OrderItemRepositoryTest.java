package com.nhnacademy.springbootjpa.repository;

import com.nhnacademy.springbootjpa.entity.OrderItem;
import com.nhnacademy.springbootjpa.entity.OrderItemPk;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

// TODO #3: 아래 `@Disabled` 어노테이션을 삭제하고 테스트를 통과시키세요.
@DataJpaTest
class OrderItemRepositoryTest {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Sql("order-item-test.sql")
    @Test
    void findOrderItemTest() {
        // given
        long orderId = 1;
        int lineNumber = 1;
        OrderItemPk orderItemPk = new OrderItemPk(orderId, lineNumber);

        // when
        OrderItem orderItem = orderItemRepository.findById(orderItemPk).orElse(null);

        // then
        assertThat(orderItem).isNotNull();
        assertThat(orderItem.getPk().getOrderId()).isEqualTo(orderId);
        assertThat(orderItem.getPk().getLineNumber()).isEqualTo(lineNumber);
        assertThat(orderItem.getItemId()).isEqualTo(1L);
        assertThat(orderItem.getQuantity()).isEqualTo(3);
    }

}
