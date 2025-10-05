package com.nhnacademy.shoppingmall.model.product.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.model.product.domain.Product;
import com.nhnacademy.shoppingmall.model.product.repository.ProductRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {

    private Product mapRow(ResultSet rs) throws SQLException {
        return new Product(
                rs.getInt("ProductID"),
                rs.getString("ModelNumber"),
                rs.getString("ModelName"),
                rs.getString("ProductImage"),
                rs.getInt("UnitPrice"),
                rs.getString("Description"),
                rs.getInt("CategoryID")
        );
    }

    @Override
    public Optional<Product> findById(int productId) {
        final String sql =
                "SELECT ProductID, ModelNumber, ModelName, ProductImage, UnitPrice, Description, CategoryID " +
                        "FROM Products WHERE ProductID = ?";
        Connection conn = DbConnectionThreadLocal.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Product> findByModelNumber(String modelNumber) {
        final String sql =
                "SELECT ProductID, ModelNumber, ModelName, ProductImage, UnitPrice, Description, CategoryID " +
                        "FROM Products WHERE ModelNumber = ?";
        Connection conn = DbConnectionThreadLocal.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, modelNumber);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findAllOrderByNewest() {
        final String sql =
                "SELECT ProductID, ModelNumber, ModelName, ProductImage, UnitPrice, Description, CategoryID " +
                        "FROM Products ORDER BY ProductID DESC";
        Connection conn = DbConnectionThreadLocal.getConnection();
        List<Product> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Product> findPageOrderByNewest(int offset, int limit) {
        final String sql =
                "SELECT ProductID, ModelNumber, ModelName, ProductImage, UnitPrice, Description, CategoryID " +
                        "FROM Products ORDER BY ProductID DESC LIMIT ? OFFSET ?";
        Connection conn = DbConnectionThreadLocal.getConnection();
        List<Product> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public int countAll() {
        final String sql = "SELECT COUNT(*) FROM Products";
        Connection conn = DbConnectionThreadLocal.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int save(Product p) {
        final String sql =
                "INSERT INTO Products(ModelNumber, ModelName, ProductImage, UnitPrice, Description, CategoryID) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = DbConnectionThreadLocal.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getModelNumber());
            ps.setString(2, p.getModelName());
            ps.setString(3, p.getProductImage());
            ps.setInt(4, p.getUnitPrice());
            ps.setString(5, p.getDescription());
            if (p.getCategoryId() > 0) ps.setInt(6, p.getCategoryId()); else ps.setNull(6, Types.INTEGER);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Product p) {
        final String sql =
                "UPDATE Products SET ModelNumber=?, ModelName=?, ProductImage=?, UnitPrice=?, Description=?, CategoryID=? " +
                        "WHERE ProductID=?";
        Connection conn = DbConnectionThreadLocal.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getModelNumber());
            ps.setString(2, p.getModelName());
            ps.setString(3, p.getProductImage());
            ps.setInt(4, p.getUnitPrice());
            ps.setString(5, p.getDescription());
            if (p.getCategoryId() > 0) ps.setInt(6, p.getCategoryId()); else ps.setNull(6, Types.INTEGER);
            ps.setInt(7, p.getProductId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteById(int productId) {
        final String sql = "DELETE FROM Products WHERE ProductID = ?";
        Connection conn = DbConnectionThreadLocal.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
