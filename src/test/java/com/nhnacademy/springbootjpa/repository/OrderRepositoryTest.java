package com.nhnacademy.springbootjpa.repository;

import com.nhnacademy.springbootjpa.entity.Order;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

// TODO #2: 아래 `@Disabled` 어노테이션을 삭제하고 테스트를 통과시키세요.
@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Sql("order-test.sql")
    @Test
    void findOrderTest() {
        // given
        long id = 1L;

        // when
        Order order = orderRepository.findById(id).orElse(null);

        // then
        assertThat(order).isNotNull();
        assertThat(order.getId()).isEqualTo(id);
        assertThat(order.getOrderedAt()).isEqualTo(ZonedDateTime.parse("2018-08-23T10:30:00+09:00"));
    }

}
