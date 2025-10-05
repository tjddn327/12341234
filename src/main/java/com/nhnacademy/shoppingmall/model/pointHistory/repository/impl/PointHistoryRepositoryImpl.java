package com.nhnacademy.shoppingmall.model.pointHistory.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.model.pointHistory.domain.PointHistory;
import com.nhnacademy.shoppingmall.model.pointHistory.repository.PointHistoryRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PointHistoryRepositoryImpl implements PointHistoryRepository {
    @Override
    public int save(PointHistory pointHistory) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "INSERT INTO PointHistory (UserID, OrderID, Points, TransactionType, TransactionDate) VALUES (?, ?, ?, ?, ?)";
        log.debug("sql: {}", sql);

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, pointHistory.getUserId());
            if (pointHistory.getOrderId() != null) {
                psmt.setInt(2, pointHistory.getOrderId());
            } else {
                psmt.setNull(2, Types.INTEGER);
            }
            psmt.setInt(3, pointHistory.getPoints());
            psmt.setString(4, pointHistory.getTransactionType());
            psmt.setTimestamp(5, Timestamp.valueOf(pointHistory.getTransactionDate()));
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<PointHistory> findByUserId(Long userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM PointHistory WHERE UserID = ? ORDER BY TransactionDate DESC";
        log.debug("sql: {}", sql);
        List<PointHistory> historyList = new ArrayList<>();

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, String.valueOf(userId));
            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    historyList.add(mapToPointHistory(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return historyList;
    }
    private PointHistory mapToPointHistory(ResultSet rs) throws SQLException {
        return new  PointHistory(
                rs.getInt("PointHistoryID"),
                rs.getString("UserID"),
                rs.getObject("OrderID") != null ? rs.getInt("OrderID") : null,
                rs.getInt("Points"),
                rs.getString("TransactionType"),
                rs.getTimestamp("TransactionDate").toLocalDateTime()
        );
    }
}
