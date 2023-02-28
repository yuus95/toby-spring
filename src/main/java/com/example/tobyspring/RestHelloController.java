package com.example.tobyspring;

public class RestHelloController {
    private final HelloService helloService;

    public RestHelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    public String hello(String name) {
        return "hello " + name;
    }
}
