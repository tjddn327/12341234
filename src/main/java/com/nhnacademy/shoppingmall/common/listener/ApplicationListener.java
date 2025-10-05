package com.nhnacademy.shoppingmall.common.listener;

import com.nhnacademy.shoppingmall.model.user.domain.User;
import com.nhnacademy.shoppingmall.model.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.model.user.service.UserService;
import com.nhnacademy.shoppingmall.model.user.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

@Slf4j
public class ApplicationListener implements ServletContextListener {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //todo#12 application 시작시 테스트 계정인 admin,user 등록합니다. 만약 존재하면 등록하지 않습니다.
        String adminId = "admin";
        String adminPwd = "admin!@#";
        String userId = "user";
        String userPwd = "user!@#";
        if (userService.getUser(adminId) == null) {
            userService.saveUser(new User(
                    adminId,
                    "관리자",
                    adminPwd,
                    "20010101",
                    User.Auth.ROLE_ADMIN,
                    1_000_000,
                    java.time.LocalDateTime.now(),
                    null
            ));
        }
        if (userService.getUser(userId) == null) {
            userService.saveUser(new User(
                    userId,
                    "일반유저",
                    userPwd,
                    "20010327",
                    User.Auth.ROLE_USER,
                    1_000_000,
                    java.time.LocalDateTime.now(),
                    null
            ));
        }
    }
}
