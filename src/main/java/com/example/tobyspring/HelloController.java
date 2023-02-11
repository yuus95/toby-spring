package com.example.tobyspring;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


/**
 * 디스패치 서블렛은 클레스 단위로 먼저 매핑하고 그다음 메소드 단위를 매핑한다.
 * DisPatcherServlet 은 기본 동작은 MVC 이다 VIEW 를 찾아서 반환한다.
 * ResponseBody 어노테이션이 없을 경우 바디에 값을 넣지 않고 VIEW 값을 반환한다.
 */
@RequestMapping
@Component
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    // 스프링 컨테이너가 빈 객체를 다 뒤져서 해당 URL을 처리한다.  -> 매핑 테이블을 만든다.

    @GetMapping("/hello")
    @ResponseBody
    public String getString(@RequestParam String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
