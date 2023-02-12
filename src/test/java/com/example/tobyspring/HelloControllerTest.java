package com.example.tobyspring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 항상 정해진 값으로 들어오는게 아니다.
 * 컨테이너 없이 테스트 하는 방법 - 유닛테스트 (스프링 컨테이너를 키지 않으니간 테스트가 빠르다.)
 * 테스트코드가 빠르면 더 많은 테스트를 실행할 수 있다.
 */
class HelloControllerTest {
    private HelloController helloController;

    @BeforeEach
    public void init() {
        helloController = new HelloController(name -> name);
    }

    @Test
    public void helloTest() {
        //given
        String test = helloController.getString("test");
        //when
        Assertions.assertThat(test).isEqualTo("test");
        //then
    }

    @Test
    public void failedHelloController() {
        Assertions.assertThatThrownBy(() -> {
            helloController.getString(null);
        }).isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> {
            helloController.getString("");
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
