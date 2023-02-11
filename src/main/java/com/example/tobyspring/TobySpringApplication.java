package com.example.tobyspring;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


/**
 * 자바 어노테이션을 이용하여 스프링 구성요소를 등록
 *
 * configuration : 빈 객체를 등록하게 해준다.
 *
 * @Bean: indicates that a method produces a bean to be managed by the Spring container.
 *          - 스프링 컨테이너에서 관리되는 빈이라고 명시해준다.
 *
 * AnnotationConfigWebApplicationContext: register 메소드를 이용하여 configuration 어노테이션을 인지한다.
 */
@Configuration
public class TobySpringApplication {

    @Bean
    public HelloController helloController(HelloService helloService) {
        return new HelloController(helloService);
    }

    @Bean
    public HelloService helloService() {
        return new SimpleHelloService();
    }

    public static void main(String[] args) {
        // onRefresh
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                ServletWebServerFactory webServer = new TomcatServletWebServerFactory();
                webServer.getWebServer(servletContext -> servletContext.addServlet("dispatcherServlet",
                        new DispatcherServlet(this)
                ).addMapping("/*")).start();
            }
        };
        // Register one or more component classes to be processed.
        applicationContext.register(TobySpringApplication.class);

        applicationContext.refresh();
    }
}
