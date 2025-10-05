package com.nhnacademy.shoppingmall.model.category.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.model.category.domain.Category;
import com.nhnacademy.shoppingmall.model.category.repository.CategoryRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryRepositoryImpl implements CategoryRepository {

    @Override
    public Optional<Category> findById(int categoryId){
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM Categories WHERE CategoryID = ?";
        try (PreparedStatement psmt = connection.prepareStatement(sql)){
            psmt.setInt(1, categoryId);
            try (ResultSet rs = psmt.executeQuery()){
                if (rs.next()){
                    return Optional.of(new Category(rs.getInt("CategoryID"), rs.getString("CategoryName")));
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Category> findAll() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM Categories";
        List<Category> categories = new ArrayList<>();
        try (PreparedStatement psmt = connection.prepareStatement(sql);
             ResultSet rs = psmt.executeQuery()) {
            while (rs.next()) {
                categories.add(new Category(rs.getInt("CategoryID"), rs.getString("CategoryName")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public int save(Category category) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "INSERT INTO Categories (CategoryName) VALUES (?)";
        try (PreparedStatement psmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            psmt.setString(1,category.getCategoryName());

            int result = psmt.executeUpdate();
            if (result > 0) {
                try (ResultSet generatedKeys = psmt.getGeneratedKeys()){
                    if (generatedKeys.next()){
                        category.setCategoryId(generatedKeys.getInt(1));
                    }
                }
            }
            return result;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Category category) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "UPDATE Categories SET CategoryName = ? WHERE CategoryId = ?";
        try (PreparedStatement psmt = connection.prepareStatement(sql)){
            psmt.setString(1, category.getCategoryName());
            psmt.setInt(2, category.getCategoryId());
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteById(int categoryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "DELETE FROM Categories WHERE CategoryId = ?";
        try (PreparedStatement psmt = connection.prepareStatement(sql)){
            psmt.setInt(1, categoryId);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
