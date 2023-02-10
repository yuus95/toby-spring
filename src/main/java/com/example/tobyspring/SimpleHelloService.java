package com.example.tobyspring;

public class SimpleHelloService implements HelloService {
    @Override
    public String sayHello(String name) {
        return "simple Logic Hello" + name;
    }
}
