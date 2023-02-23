package com.example.tobyspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;


/**
 * 스프링 컨테이너가 초기화 된 이후
 * 등록된 빈을 이용하여 테스트코드 작성
 * 또는 초기화 된 이후 빈 등록
 * <p>
 * 어플리케이션 외부 환겨 변수 순서
 * 시스템 환경 변수, (application.yml, properties )
 */
@SpringBootApplication
public class TobySpringApplication {
    private final JdbcTemplate jdbcTemplate;

    public TobySpringApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    void init() {
        jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
    }

    //    @Bean
//    ApplicationRunner applicationRunner(Environment env) {
//        return args -> {
//            String property = env.getProperty("my.name");
//            System.out.println("property  " + property );
//        };
//    }

    public static void main(String[] args) {
        SpringApplication.run(TobySpringApplication.class, args);
    }
}
