package com.example.tobyspring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * 고립된 테스트가 가능하다.
 *
 */
class SimpleHelloServiceTest {
    @Test
    void simpleHelloService() {
        SimpleHelloService helloService = new SimpleHelloService();
        String result = helloService.sayHello("Test");
        Assertions.assertThat(result).isEqualTo("Hello Test");

    }
}
