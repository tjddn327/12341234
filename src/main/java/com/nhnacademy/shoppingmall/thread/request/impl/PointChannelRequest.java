package com.nhnacademy.shoppingmall.thread.request.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.thread.request.ChannelRequest;
import com.nhnacademy.shoppingmall.model.user.domain.User;
import com.nhnacademy.shoppingmall.model.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.model.user.service.UserService;
import com.nhnacademy.shoppingmall.model.user.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Objects;

@Slf4j
public class PointChannelRequest extends ChannelRequest {
    private final String userId;
    private final int points;
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    public PointChannelRequest(String userId, int points) {
        this.userId = userId;
        this.points = points;
    }

    @Override
    public void execute() throws SQLException {
        DbConnectionThreadLocal.initialize();
        try {
            //todo#14-5 포인트 적립구현, connection은 point적립이 완료되면 반납합니다.
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
            log.error("PointChannelRequest execute error: {}", e.getMessage());
            DbConnectionThreadLocal.setSqlError(true);
        } finally {
            log.debug("pointChannel execute");
            DbConnectionThreadLocal.reset();
        }
    }
}
