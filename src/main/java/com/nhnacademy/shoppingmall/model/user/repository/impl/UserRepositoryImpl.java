package com.nhnacademy.shoppingmall.model.user.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.model.user.domain.User;
import com.nhnacademy.shoppingmall.model.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> findByUserIdAndUserPassword(String userId, String userPassword) {
        /*todo#3-1 회원의 아이디와 비밀번호를 이용해서 조회하는 코드 입니다.(로그인)
          해당 코드는 SQL Injection이 발생합니다. SQL Injection이 발생하지 않도록 수정하세요.
         */
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT UserId, UserName, UserPassword, UserBirth, UserAuth, UserPoint, CreatedAt, LatestLogin_at "
                + "FROM users WHERE UserId=? AND UserPassword=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userId);
            ps.setString(2, userPassword);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = mapUser(rs);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(String userId) {
        //todo#3-2 회원조회
        Connection conn = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT UserId, UserName, UserPassword, UserBirth, UserAuth, UserPoint, CreatedAt, LatestLogin_at "
                + "FROM users WHERE UserId=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(mapUser(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public int save(User user) {
        //todo#3-3 회원등록, executeUpdate()을 반환합니다.
        Connection conn = DbConnectionThreadLocal.getConnection();
        String sql = "INSERT INTO users(UserId, UserName, UserPassword, UserBirth, UserAuth, UserPoint, CreatedAt, LatestLogin_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getUserPassword());
            ps.setString(4, user.getUserBirth());
            ps.setString(5, user.getUserAuth().toString());
            ps.setInt(6, user.getUserPoint());
            ps.setTimestamp(7, Timestamp.valueOf(user.getCreatedAt()));
            ps.setTimestamp(8, user.getLatestLoginAt() != null ? Timestamp.valueOf(user.getLatestLoginAt()) : null);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByUserId(String userId) {
        //todo#3-4 회원삭제, executeUpdate()을 반환합니다.
        Connection conn = DbConnectionThreadLocal.getConnection();
        String sql = "DELETE FROM users WHERE UserId=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(User user) {
        //todo#3-5 회원수정, executeUpdate()을 반환합니다.
        Connection conn = DbConnectionThreadLocal.getConnection();
        String sql = "UPDATE users SET UserName=?, UserPassword=?, UserBirth=?, UserAuth=?, UserPoint=?, LatestLogin_at=? WHERE UserId=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getUserPassword());
            ps.setString(3, user.getUserBirth());
            ps.setString(4, user.getUserAuth().toString());
            ps.setInt(5, user.getUserPoint());
            ps.setTimestamp(6, user.getLatestLoginAt() != null ? Timestamp.valueOf(user.getLatestLoginAt()) : null);
            ps.setString(7, user.getUserId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateLatestLoginAtByUserId(String userId, LocalDateTime latestLoginAt) {
        //todo#3-6, 마지막 로그인 시간 업데이트, executeUpdate()을 반환합니다.
        Connection conn = DbConnectionThreadLocal.getConnection();
        String sql = "UPDATE users SET LatestLogin_at=? WHERE UserId=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(latestLoginAt));
            ps.setString(2, userId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByUserId(String userId) {
        //todo#3-7 userId와 일치하는 회원의 count를 반환합니다.
        Connection conn = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT COUNT(*) FROM users WHERE UserId=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public List<User> findAll() {
        final String sql =
                "SELECT UserId, UserName, UserPassword, UserBirth, " +
                        "       UserAuth, UserPoint, CreatedAt, LatestLogin_at " +
                        "FROM Users " +
                        "ORDER BY UserAuth ASC, CreatedAt DESC";

        Connection conn = DbConnectionThreadLocal.getConnection();
        List<User> users = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                users.add(mapUser(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }


    private User mapUser(ResultSet rs) throws SQLException {
        Timestamp createdTs = rs.getTimestamp("CreatedAt");
        Timestamp latestTs  = rs.getTimestamp("LatestLogin_at");

        return new User(
                rs.getString("UserId"),
                rs.getString("UserName"),
                rs.getString("UserPassword"),
                rs.getString("UserBirth"),
                User.Auth.valueOf(rs.getString("UserAuth")),
                rs.getInt("UserPoint"),
                createdTs != null ? createdTs.toLocalDateTime() : null,
                latestTs != null ? latestTs.toLocalDateTime() : null
        );
    }


}