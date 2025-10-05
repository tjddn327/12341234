package com.nhnacademy.shoppingmall.model.cart.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.model.cart.domain.Cart;
import com.nhnacademy.shoppingmall.model.cart.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CartRepositoryImpl implements CartRepository {

    @Override
    public int save(Cart cart) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "INSERT INTO Cart (UserID, ProductID, Quantity, CreatedAt) VALUES (?, ?, ?, NOW()) ON DUPLICATE KEY UPDATE Quantity = Quantity + VALUES(Quantity)";
        log.debug("sql: {}", sql);

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, cart.getUserId());
            psmt.setInt(2, cart.getProductId());
            psmt.setInt(3, cart.getQuantity());
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Cart> findByUserId(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT UserID, ProductID, Quantity FROM Cart WHERE UserID = ?";
        log.debug("sql: {}", sql);
        List<Cart> cartItems = new ArrayList<>();

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);
            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    cartItems.add(new Cart(
                            rs.getString("UserID"),
                            rs.getInt("ProductID"),
                            rs.getInt("Quantity")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartItems;
    }

    @Override
    public int deleteByUserIdAndProductId(String userId, int productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "DELETE FROM Cart WHERE UserID = ? AND ProductID = ?";
        log.debug("sql: {}", sql);

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);
            psmt.setInt(2, productId);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByUserIdAndProductId(String userId, int productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT COUNT(*) FROM Cart WHERE UserID = ? AND ProductID = ?";
        log.debug("sql: {}", sql);

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);
            psmt.setInt(2, productId);
            try (ResultSet rs = psmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}

