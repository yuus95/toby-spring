package com.example.tobyspring.config.autoconfig;

import com.example.tobyspring.config.MyAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 어떤 기술을 정확히 알고 싶을 때
 * 테스트 코드를 이용하여 배울 수 있다.
 *
 * 스프링 : 톰캣, 제티, 언더토우 의 임베디드 서블렛 컨테이너를 사용할 수 있게 해준다.
 */
@MyAutoConfiguration
public class DispatcherConfig {
    @Bean
    public DispatcherServlet dispatcherServlet(WebApplicationContext webApplicationContext) {
        return new DispatcherServlet(webApplicationContext);
    }
}

