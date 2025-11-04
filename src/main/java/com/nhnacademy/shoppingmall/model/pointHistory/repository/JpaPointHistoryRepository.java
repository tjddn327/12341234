package com.nhnacademy.shoppingmall.model.pointHistory.repository;

import com.nhnacademy.shoppingmall.model.pointHistory.domain.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JpaPointHistoryRepository extends JpaRepository<PointHistory, Integer> {

    // PointHistory 엔티티의 'user' 필드 내부의 'userId' 필드를 찾음
    List<PointHistory> findByUser_UserIdOrderByTransactionDateDesc(String userId);
}