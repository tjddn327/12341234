package com.nhnacademy.shoppingmall.model.user.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Users")
public class User {

    public enum Auth {
        ROLE_ADMIN, ROLE_USER
    }

    @Id
    @Column(name = "UserId", length = 50)
    private String userId;

    @Column(name = "UserName", nullable = false, length = 50)
    private String userName;

    @Column(name = "UserPassword", nullable = false, length = 200)
    private String userPassword;

    @Column(name = "UserBirth", nullable = false, length = 8)
    private String userBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "UserAuth", nullable = false, length = 10)
    private Auth userAuth;

    @Column(name = "UserPoint", nullable = false)
    private int userPoint;

    @Column(name = "CreatedAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "LatestLogin_at")
    private LocalDateTime latestLoginAt;

    // JPA를 위한 기본 생성자
    protected User() {
    }

    public User(String userId, String userName, String userPassword, String userBirth, Auth userAuth, int userPoint, LocalDateTime createdAt, LocalDateTime latestLoginAt) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userBirth = userBirth;
        this.userAuth = userAuth;
        this.userPoint = userPoint;
        this.createdAt = createdAt;
        this.latestLoginAt = latestLoginAt;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserBirth() {
        return userBirth;
    }

    public Auth getUserAuth() {
        return userAuth;
    }

    public int getUserPoint() {
        return userPoint;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLatestLoginAt() {
        return latestLoginAt;
    }

    // --- 원본 Setter ---

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserBirth(String userBirth) {
        this.userBirth = userBirth;
    }

    public void setUserAuth(Auth userAuth) {
        this.userAuth = userAuth;
    }

    public void setUserPoint(int userPoint) {
        this.userPoint = userPoint;
    }

    public void setLatestLoginAt(LocalDateTime latestLoginAt) {
        this.latestLoginAt = latestLoginAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userPoint == user.userPoint &&
                Objects.equals(userId, user.userId) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(userPassword, user.userPassword) &&
                Objects.equals(userBirth, user.userBirth) &&
                userAuth == user.userAuth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userPassword, userBirth, userAuth, userPoint);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userBirth='" + userBirth + '\'' +
                ", userAuth=" + userAuth +
                ", userPoint=" + userPoint +
                ", createdAt=" + createdAt +
                ", latestLoginAt=" + latestLoginAt +
                '}';
    }
}