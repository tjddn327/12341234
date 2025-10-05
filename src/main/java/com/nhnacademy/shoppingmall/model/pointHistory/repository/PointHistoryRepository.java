package com.nhnacademy.shoppingmall.model.pointHistory.repository;

import com.nhnacademy.shoppingmall.model.pointHistory.domain.PointHistory;

import java.util.List;

public interface PointHistoryRepository {
    int save(PointHistory pointHistory);
    List<PointHistory> findByUserId(Long userId);
}
