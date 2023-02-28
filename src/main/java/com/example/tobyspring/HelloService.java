package com.example.tobyspring;

/*
HelloService 관련 의존성있는 객체들은 해당 인터페이스의 구현체를 의존받아서 사용하면된다.
해당 구현체와 의존받는 객체를 빈객체로 등록한 뒤 스프링컨테이너를 실행한다면
스프링이 알아서 의존성을 주입해준다.
 */
public interface HelloService {

    public String sayHello(String name);
}
