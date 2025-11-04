package com.nhnacademy.shoppingmall.model.user.repository;

import com.nhnacademy.shoppingmall.model.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User, String> {
    
    Optional<User> findByUserIdAndUserPassword(String userId, String userPassword);

    long countByUserId(String userId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE User u SET u.latestLoginAt = :latestLoginAt WHERE u.userId = :userId")
    int updateLatestLoginAtByUserId(@Param("userId") String userId, @Param("latestLoginAt") LocalDateTime latestLoginAt);
}