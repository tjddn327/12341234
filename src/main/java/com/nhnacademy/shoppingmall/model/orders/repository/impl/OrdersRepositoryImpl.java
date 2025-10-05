package com.nhnacademy.shoppingmall.model.orders.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.model.orders.domain.Orders;
import com.nhnacademy.shoppingmall.model.orders.repository.OrdersRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class OrdersRepositoryImpl implements OrdersRepository {

    @Override
    public Optional<Orders> findById(int orderId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM Orders WHERE OrderID = ?";
        log.debug("sql: {}", sql);

        try (PreparedStatement psmt = connection.prepareStatement(sql)){
            psmt.setInt(1, orderId);
            try (ResultSet rs = psmt.executeQuery()){
                if(rs.next()) {
                    return Optional.of(mapToOrder(rs));
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public int save(Orders order) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "INSERT INTO Orders (UserID, OrderDate, ShipAddress, TotalPrice) VALUES (?, ?, ?, ?)";
        log.debug("sql: {}", sql);

        try (PreparedStatement psmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            psmt.setString(1, order.getUserId());
            psmt.setTimestamp(2, Timestamp.valueOf(order.getOrderDate()));
            psmt.setString(3, order.getShipAddress());
            psmt.setInt(4, order.getTotalPrice());

            int result = psmt.executeUpdate();
            if (result > 0) {
                try (ResultSet generatedKeys = psmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        order.setOrderId(generatedKeys.getInt(1));
                    }
                }
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int deleteByOrderId(int orderId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "DELETE FROM Orders WHERE OrderID = ?";
        log.debug("sql: {}", sql);

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, orderId);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Orders order) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "UPDATE Orders SET UserID = ?, OrderDate = ?, ShipAddress = ?, TotalPrice = ? WHERE OrderID = ?";
        log.debug("sql: {}", sql);

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, order.getUserId());
            psmt.setTimestamp(2, Timestamp.valueOf(order.getOrderDate()));
            psmt.setString(3, order.getShipAddress());
            psmt.setInt(4, order.getTotalPrice());
            psmt.setInt(5, order.getOrderId());
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Orders> findByUserId(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM Orders WHERE UserID = ? ORDER BY OrderDate DESC";
        log.debug("sql: {}", sql);
        List<Orders> orders = new ArrayList<>();

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);
            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    orders.add(mapToOrder(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    private Orders mapToOrder(ResultSet rs) throws SQLException {
        return new Orders(
                rs.getInt("OrderID"),
                rs.getString("UserID"),
                rs.getTimestamp("OrderDate").toLocalDateTime(),
                rs.getString("ShipAddress"),
                rs.getInt("TotalPrice")
        );
    }
    @Override
    public List<Orders> findAll() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM Orders ORDER BY OrderDate DESC";
        List<Orders> orderList = new ArrayList<>();

        try (PreparedStatement psmt = connection.prepareStatement(sql);
             ResultSet rs = psmt.executeQuery()) {
            while (rs.next()) {
                Orders order = new Orders(
                        rs.getInt("OrderID"),
                        rs.getString("UserID"),
                        rs.getTimestamp("OrderDate").toLocalDateTime(),
                        rs.getString("ShipAddress"),
                        rs.getInt("TotalPrice")
                );
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderList;
    }
}
