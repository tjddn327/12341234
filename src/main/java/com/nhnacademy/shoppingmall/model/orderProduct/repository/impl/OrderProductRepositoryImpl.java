package com.nhnacademy.shoppingmall.model.orderProduct.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.model.orderProduct.domain.OrderProduct;
import com.nhnacademy.shoppingmall.model.orderProduct.repository.OrderProductRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderProductRepositoryImpl implements OrderProductRepository {
    @Override
    public int save(OrderProduct orderProduct) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "INSERT INTO OrderDetails (OrderID, ProductID, Quantity, UnitPrice) VALUES (?, ?, ?, ?)";
        log.debug("sql: {}", sql);

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, orderProduct.getOrderId());
            psmt.setInt(2, orderProduct.getProductId());
            psmt.setInt(3, orderProduct.getQuantity());
            psmt.setInt(4, orderProduct.getUnitPrice());
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderProduct> findByOrderId(int orderId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM OrderDetails WHERE OrderID = ?";
        log.debug("sql: {}", sql);
        List<OrderProduct> orderProducts = new ArrayList<>();

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, orderId);
            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    orderProducts.add(new OrderProduct(
                            rs.getInt("OrderID"),
                            rs.getInt("ProductID"),
                            rs.getInt("Quantity"),
                            rs.getInt("UnitPrice")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderProducts;
    }
}
