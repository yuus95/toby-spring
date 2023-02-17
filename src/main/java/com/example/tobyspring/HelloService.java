package com.example.tobyspring;

public interface HelloService {
    String sayHello(String name);


    // 디폴트 메서드는 구현체를 필수적으로 구현하지 않아도된다.
    // sayHello 구현부를 익명 클래스를 활용하여 테스트코드를 진행하고 싶으면 countOf의 타입을 default method 로  바꾸어 기본 구현체를 납두면 된다.
    default int countOf(String name) {
        return 0;
    }

    ;
}
