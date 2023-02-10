package com.example.tobyspring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping
    public String getString(@RequestParam String name) {
        SimpleHelloService simpleHelloService = new SimpleHelloService();


        return simpleHelloService.sayHello(Objects.requireNonNull(name));

    }
}
