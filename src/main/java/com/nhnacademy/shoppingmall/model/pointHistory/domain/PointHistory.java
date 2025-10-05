package com.nhnacademy.shoppingmall.model.pointHistory.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointHistory {
    private int pointHistoryId;
    private String userId;
    private Integer orderId;
    private int points;
    private String transactionType;
    private LocalDateTime transactionDate;
}
