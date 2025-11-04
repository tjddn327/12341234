package com.nhnacademy.shoppingmall.common.initialize;

import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.mvc.controller.ControllerFactory;
import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;
import java.util.Set;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Slf4j
@HandlesTypes(
        value = {
                BaseController.class
        }
)
public class WebAppInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(ctx);

        if (springContext == null) {
            log.error("Spring WebApplicationContext not found!");
            throw new ServletException("Spring context not initialized. Check web.xml for ContextLoaderListener.");
        }

        ControllerFactory controllerFactory = new ControllerFactory();
        controllerFactory.initialize(springContext, ctx);
    }
}