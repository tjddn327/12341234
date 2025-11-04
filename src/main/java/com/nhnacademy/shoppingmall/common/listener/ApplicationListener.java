package com.nhnacademy.shoppingmall.common.listener;

import com.nhnacademy.shoppingmall.model.user.domain.User;
import com.nhnacademy.shoppingmall.model.user.service.UserService;
import com.nhnacademy.shoppingmall.thread.channel.RequestChannel;
import com.nhnacademy.shoppingmall.thread.worker.WorkerThread;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Slf4j
@Component
public class ApplicationListener {

    private final UserService userService;
    private final ServletContext servletContext;

    @Autowired
    public ApplicationListener(UserService userService, ServletContext servletContext) {
        this.userService = userService;
        this.servletContext = servletContext;
    }

    @PostConstruct
    public void init() {
        log.info("ApplicationListener: Context Initialized. Running init().");
        createDefaultUsers();
        startPointWorkerThread();
    }

    private void createDefaultUsers() {
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

    private void startPointWorkerThread() {
        RequestChannel requestChannel = (RequestChannel) servletContext.getAttribute("requestChannel");
        if (requestChannel == null) {
            log.error("RequestChannel not found in ServletContext!");
            return;
        }

        WorkerThread worker = new WorkerThread(requestChannel, userService);
        worker.setDaemon(true);
        worker.start();
        log.info("Point worker thread started.");
    }
}