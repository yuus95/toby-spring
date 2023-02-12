package com.example.tobyspring;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Decorator 방식
 * Indicates that a bean should be given preference
 * when multiple candidates are qualified to autowire
 * a single-valued dependency. If exactly one 'primary'
 * bean exists among the candidates,
 * it will be the autowired value
 *
 * HelloService 를 위임받아 대신 일을 처리한다.
 * 장점으로는 기본 구현을 변경시키지 않고 추가 적인 로직을 적용할 수 있다.
 */
@Service

/**
 *  하나의 인터페이스의 구현체가 여러 개일 경우 해당 인터페이스의 구현체를 선언할 수 있다.
 *  HelloController -> HelloDecorator -> SimpleHelloService
 */
@Primary
public class HelloDecorator implements HelloService {
    private final HelloService helloService;

    public HelloDecorator(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public String sayHello(String name) {
        return "*" + helloService.sayHello(name) + "*";
    }
}
