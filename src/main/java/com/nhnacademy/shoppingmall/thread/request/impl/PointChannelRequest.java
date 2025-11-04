package com.nhnacademy.shoppingmall.thread.request.impl;

import com.nhnacademy.shoppingmall.thread.request.ChannelRequest;
import com.nhnacademy.shoppingmall.model.user.domain.User;
import com.nhnacademy.shoppingmall.model.user.service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Objects;

@Slf4j
public class PointChannelRequest extends ChannelRequest {
    private final String userId;
    private final int points;
    private UserService userService;

    public PointChannelRequest(String userId, int points) {
        this.userId = userId;
        this.points = points;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute() throws SQLException {
        if (this.userService == null) {
            log.error("UserService not injected for PointChannelRequest!");
            return;
        }

        try {
            User user = userService.getUser(userId);
            if (Objects.nonNull(user)) {
                int newPoint = user.getUserPoint() + this.points;
                user.setUserPoint(newPoint);
                userService.updateUser(user);
                log.info("Point updated for user: {}, new point: {}", userId, newPoint);
            } else {
                log.warn("User not found for point update: {}", userId);
            }
        } catch (Exception e) {
            log.error("PointChannelRequest execute error: {}", e.getMessage(), e);
        }
    }
}