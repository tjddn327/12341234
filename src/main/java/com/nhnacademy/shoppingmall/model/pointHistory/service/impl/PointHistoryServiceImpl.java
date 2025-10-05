package com.nhnacademy.shoppingmall.model.pointHistory.service.impl;


import com.nhnacademy.shoppingmall.model.pointHistory.domain.PointHistory;
import com.nhnacademy.shoppingmall.model.pointHistory.repository.PointHistoryRepository;
import com.nhnacademy.shoppingmall.model.pointHistory.service.PointHistoryService;

import java.util.List;

public class PointHistoryServiceImpl implements PointHistoryService {
    private final PointHistoryRepository pointHistoryRepository;

    public PointHistoryServiceImpl(PointHistoryRepository pointHistoryRepository) {
        this.pointHistoryRepository = pointHistoryRepository;
    }

    @Override
    public void savePointHistory(PointHistory pointHistory) {
        pointHistoryRepository.save(pointHistory);
    }

    @Override
    public List<PointHistory> getPointHistoryByUserId(String userId) {
        return pointHistoryRepository.findByUserId(Long.valueOf(userId));
    }
}
