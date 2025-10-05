package com.nhnacademy.shoppingmall.common.initialize;

import com.nhnacademy.shoppingmall.thread.channel.RequestChannel;
import com.nhnacademy.shoppingmall.thread.request.ChannelRequest;
import com.nhnacademy.shoppingmall.thread.worker.WorkerThread;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

import java.sql.SQLException;
import java.util.Set;

public class PointThreadInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {

        RequestChannel requestChannel = new RequestChannel(10);
        //todo#14-1 servletContext에 requestChannel을 등록합니다.
        ctx.setAttribute("requestChannel", requestChannel);

        //todo#14-2 WorkerThread 사작합니다.
        Thread worker = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    ChannelRequest req = requestChannel.getRequest();
                    req.execute();
                }
            } catch (InterruptedException | SQLException e) {
                Thread.currentThread().interrupt();
            }
        });
        worker.setDaemon(true);
        worker.start();

    }
}
