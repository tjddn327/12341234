package com.nhnacademy.shoppingmall.thread.worker;

import com.nhnacademy.shoppingmall.model.user.service.UserService;
import com.nhnacademy.shoppingmall.thread.channel.RequestChannel;
import com.nhnacademy.shoppingmall.thread.request.ChannelRequest;
import com.nhnacademy.shoppingmall.thread.request.impl.PointChannelRequest;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@Slf4j
public class WorkerThread extends Thread{
    private final RequestChannel requestChannel;
    private final UserService userService;

    public WorkerThread(RequestChannel requestChannel, UserService userService) {
        this.requestChannel = requestChannel;
        this.userService = userService;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                ChannelRequest channelRequest = requestChannel.getRequest();

                if (channelRequest instanceof PointChannelRequest) {
                    ((PointChannelRequest) channelRequest).setUserService(userService);
                }

                channelRequest.execute();
            } catch (InterruptedException e) {
                log.warn("WorkerThread interrupted.");
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                log.error("Error in WorkerThread execution: {}", e.getMessage(), e);
            }
        }
    }
}