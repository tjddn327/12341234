package com.nhnacademy.shoppingmall.model.orderAddress.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.model.orderAddress.domain.OrderAddress;
import com.nhnacademy.shoppingmall.model.orderAddress.repository.OrderAddressRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.Optional;

@Slf4j
public class OrderAddressRepositoryImpl implements OrderAddressRepository {

    @Override
    public int save(OrderAddress orderAddress) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "INSERT INTO OrderAddresses (OrderID, ZipCode, Address, AddressDetail) VALUES (?, ?, ?, ?)";
        try (PreparedStatement psmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            psmt.setInt(1, orderAddress.getOrderId());
            psmt.setString(2, orderAddress.getZipCode());
            psmt.setString(3, orderAddress.getAddress());
            psmt.setString(4, orderAddress.getAddressDetail());
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<OrderAddress> findByOrderId(int orderId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM OrderAddresses WHERE OrderID = ?";
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, orderId);
            try (ResultSet rs = psmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new OrderAddress(
                            rs.getInt("AddressID"),
                            rs.getInt("OrderID"),
                            rs.getString("ZipCode"),
                            rs.getString("Address"),
                            rs.getString("AddressDetail")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}