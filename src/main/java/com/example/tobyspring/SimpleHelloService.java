package com.example.tobyspring;

import org.springframework.stereotype.Component;

@Component
public class SimpleHelloService implements HelloService {
    @Override
    public String sayHello(String name) {
        return "simple Logic Hello" + name;
    }
}
