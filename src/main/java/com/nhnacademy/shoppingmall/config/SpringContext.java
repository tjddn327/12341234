package com.nhnacademy.shoppingmall.config;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

/**
 * Spring의 ApplicationContext를 정적으로 저장하여,
 * Spring Bean이 아닌 클래스(레거시 코드)에서 Bean을 조회할 수 있도록 지원하는 유틸리티 클래스입니다.
 */
public class SpringContext {
    private static WebApplicationContext context;

    /**
     * ApplicationListener에서 WebApplicationContext를 주입받아 설정합니다.
     */
    public static void setContext(WebApplicationContext ctx) {
        context = ctx;
    }

    /**
     * 저장된 Context에서 원하는 타입의 Bean을 조회합니다.
     */
    public static <T> T getBean(Class<T> beanClass) {
        if (context == null) {
            throw new IllegalStateException("Spring WebApplicationContext가 초기화되지 않았습니다.");
        }
        return context.getBean(beanClass);
    }
}