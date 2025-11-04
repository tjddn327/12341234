package com.nhnacademy.shoppingmall.common.mvc.controller;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.exception.ControllerNotFoundException;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.web.context.WebApplicationContext;

@Slf4j
public class ControllerFactory {

    public static final String CONTEXT_CONTROLLER_FACTORY_NAME="CONTEXT_CONTROLLER_FACTORY";
    private final ConcurrentMap<String, Object> beanMap = new ConcurrentHashMap<>();

    public void initialize(WebApplicationContext springContext, ServletContext ctx){

        Map<String, Object> controllerBeans = springContext.getBeansWithAnnotation(RequestMapping.class);

        if(controllerBeans.isEmpty()){
            log.warn("No controllers found with @RequestMapping annotation.");
        }

        for (Object controller : controllerBeans.values()) {
            Class<?> clazz = controller.getClass();
            if (!clazz.isAnnotationPresent(RequestMapping.class)) {
                clazz = clazz.getSuperclass();
            }
            if (!clazz.isAnnotationPresent(RequestMapping.class)) {
                log.warn("Could not find @RequestMapping on controller bean or its superclass: {}", controller.getClass().getName());
                continue;
            }

            RequestMapping mapping = clazz.getAnnotation(RequestMapping.class);
            String method = mapping.method().name();

            for (String url : mapping.value()) {
                String key = getKey(method, url);
                beanMap.put(key, controller);
                log.info("Mapped: {} -> {}", key, controller.getClass().getName());
            }
        }

        ctx.setAttribute(CONTEXT_CONTROLLER_FACTORY_NAME, this);
    }

    private Object getBean(String key){
        return beanMap.get(key);
    }

    public Object getController(HttpServletRequest request){
        String method = request.getMethod();
        String servletPath = request.getServletPath();
        String key = getKey(method, servletPath);
        Object controller = getBean(key);
        if (controller == null) {
            throw new ControllerNotFoundException(key);
        }
        return controller;
    }

    public Object getController(String method, String path){
        String key = getKey(method, path);
        Object controller = getBean(key);
        if (controller == null) {
            throw new ControllerNotFoundException(key);
        }
        return controller;
    }

    private String getKey(String method, String path){
        String p = (path == null || path.isEmpty()) ? "/" : path;
        if (!p.startsWith("/")) p = "/" + p;
        return method.toUpperCase() + "-" + p;
    }
}