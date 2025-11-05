package com.nhnacademy.springbootjpa.repository;

import com.nhnacademy.springbootjpa.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    /**
     * TODO #1: 메서드 이름 규칙에서 연관 관계 Entity를 이용한 JOIN 쿼리 실행 - 1차 탐색
     * 주문 수량이 매개변수로 주어진 수량보다 많은 Item 목록을 조회한다.
     *
     * @param quantity 기준 주문 수량
     * @return Item 목록
     */
    /*
    select i.*
    from item i
    join order_item oi on i.item_id = oi.item_id
    where oi.quantity > :quantity
    */
    List<Item> findAllByOrderItemsQuantityGreaterThan(int quantity);

    /**
     * TODO #2: 메서드 이름 규칙에서 연관 관계 Entity를 이용한 JOIN 쿼리 실행 - 2차 탐색
     * 주문 시간이 매개변수로 주어진 시간 이후인 Item 목록을 조회한다.
     *
     * @param orderedAt 기준 주문 시간
     * @return Item 목록
     */
    /*
    select i.*
    from item i
    join order_item oi on i.item_id = oi.item_id
    join orders o on oi.order_id = o.order_id
    where o.ordered_at > :orderedAt
    */
    List<Item> findAllByOrderItemsOrderOrderedAtAfter(ZonedDateTime orderedAt);
}
