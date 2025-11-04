package com.nhnacademy.shoppingmall.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement // 1. 트랜잭션 관리 기능 활성화
@EnableJpaRepositories(basePackages = "com.nhnacademy.shoppingmall.model") // 2. JpaRepository 스캔
@ComponentScan(basePackages = "com.nhnacademy.shoppingmall") // 3. @Service, @Component 등 스캔
public class JpaConfig {

    /**
     * 4. DataSource 빈 등록 (DBCP2 사용)
     * DB 연결 정보를 설정합니다.
     */
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        // ★★★ 사용자 환경에 맞게 DB URL, 아이디, 비밀번호를 수정하세요 ★★★
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/seong"); 
        dataSource.setUsername("root"); 
        dataSource.setPassword("YOUR_DB_PASSWORD"); // ★★★ DB 비밀번호 ★★★

        // DBCP2 상세 설정
        dataSource.setInitialSize(10);
        dataSource.setMaxTotal(20);
        dataSource.setMaxIdle(10);
        dataSource.setMinIdle(10);
        dataSource.setMaxWaitMillis(1000);
        
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);

        return dataSource;
    }

    /**
     * 5. EntityManagerFactory 빈 등록
     * JPA의 핵심 설정(엔티티 스캔, 하이버네이트 설정)을 담당합니다.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.nhnacademy.shoppingmall.model"); // @Entity가 있는 최상위 패키지
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter()); // JPA 구현체로 Hibernate 사용

        // 하이버네이트 상세 설정
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect"); // 방언 설정
        properties.setProperty("hibernate.hbm2ddl.auto", "validate"); // (중요) DDL 자동 생성 안함 (validate: 스키마 검증)
        properties.setProperty("hibernate.show_sql", "true"); // SQL 쿼리 로그
        properties.setProperty("hibernate.format_sql", "true"); // SQL 쿼리 예쁘게
        properties.setProperty("hibernate.use_sql_comments", "true"); // SQL 코멘트

        emf.setJpaProperties(properties);
        return emf;
    }

    /**
     * 6. TransactionManager 빈 등록
     * 트랜잭션(@Transactional)을 관리합니다.
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}