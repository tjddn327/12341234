package com.nhnacademy.shoppingmall.model.pointHistory.service;

import com.nhnacademy.shoppingmall.model.pointHistory.domain.PointHistory;

import java.util.List;

public interface PointHistoryService {
    void savePointHistory(PointHistory pointHistory);
    List<PointHistory> getPointHistoryByUserId(String userId);
}
