package com.nhnacademy.shoppingmall.model.user.service;

import com.nhnacademy.shoppingmall.model.user.domain.User;
import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    User getUser(String userId);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(String userId);
    User doLogin(String userId, String userPassword);
    List<User> getAllUser();

    // [추가] 스레드에서 사용할 포인트 업데이트 메서드
    void updateUserPoints(String userId, int points);
}