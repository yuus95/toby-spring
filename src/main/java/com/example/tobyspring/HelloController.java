package com.example.tobyspring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController("/hello")
public class HelloController {

    @GetMapping
    public String getString(@RequestParam String name) {
        SimpleHelloService simpleHelloService = new SimpleHelloService();


        return simpleHelloService.sayHello(Objects.requireNonNull(name));

    }
}
