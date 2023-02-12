package com.example.tobyspring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 고립된 테스트가 가능하다.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Test // 메타 어노테이션을 적용
@interface UnitTest{

}
class SimpleHelloServiceTest {
    @UnitTest
    void simpleHelloService() {
        SimpleHelloService helloService = new SimpleHelloService();
        String result = helloService.sayHello("Test");
        Assertions.assertThat(result).isEqualTo("Hello Test");

    }
}
