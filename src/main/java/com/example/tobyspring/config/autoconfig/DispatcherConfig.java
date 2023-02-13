package com.example.tobyspring.config.autoconfig;

import com.example.tobyspring.config.MyAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 어떤 기술을 정확히 알고 싶을 때
 * 테스트 코드를 이용하여 배울 수 있다.
 */
@MyAutoConfiguration
public class DispatcherConfig {
    @Bean
    public DispatcherServlet dispatcherServlet(WebApplicationContext webApplicationContext) {
        return new DispatcherServlet(webApplicationContext);
    }
}

