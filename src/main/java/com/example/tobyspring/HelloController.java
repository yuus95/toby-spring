package com.example.tobyspring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 디스패치 서블렛은 클레스 단위로 먼저 매핑하고 그다음 메소드 단위를 매핑한다.
 * DisPatcherServlet 은 기본 동작은 MVC 이다 VIEW 를 찾아서 반환한다.
 * ResponseBody 어노테이션이 없을 경우 바디에 값을 넣지 않고 VIEW 값을 반환한다.
 */
@RestController
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String getString(@RequestParam String name) {
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException();
        }
        return helloService.sayHello(name);
    }
}
