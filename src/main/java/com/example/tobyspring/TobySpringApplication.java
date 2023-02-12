package com.example.tobyspring;

import org.springframework.boot.SpringApplication;

/**
 * 메타 어노테이션
 * - 메타 어노테이션을 갖고 있는 어노테이션을 사용하면 해당 메타 어노테이션을 사용하는 것과 똑같은 효과를 얻는다.
 * - 어노테이션에 이름을 작성하여 사용하면 해당 어노테이션이 무슨의미인지 쉽게 부여할 수 있다.
 */
@MySpringApplication
public class TobySpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(TobySpringApplication.class, args);
    }
}
