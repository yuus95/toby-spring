package com.example.tobyspring;

import com.example.tobyspring.config.MySpringApplication;
import org.springframework.boot.SpringApplication;


/**
 * 조건부 자동 구성
 * 빈에 필요한 인프라 스트럭쳘ㄹ 자동으로 구성해줘서 Autoconfig라고 불린다.
 *
 */
@MySpringApplication
public class TobySpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(TobySpringApplication.class, args);
    }
}
