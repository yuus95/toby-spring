package com.example.tobyspring;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 자동 구성이 필요한 이유
 * 유저 구성정보를 먼저 넣은 뒤, 다음에 필요한 구성정보를 자동 구성정보를 통해서 가져오기 위해서.
 * 유저 구성정보가 우선순위이다.
 */
@Configuration(proxyBeanMethods = false)
public class WebServerConfiguration {
    @Bean
    ServletWebServerFactory customerWebServerFactory() {
        TomcatServletWebServerFactory servletWebServerFactory = new TomcatServletWebServerFactory();
        servletWebServerFactory.setPort(9090);
        return servletWebServerFactory;
    }

}
