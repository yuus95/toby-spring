package com.example.tobyspring;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.Encoding;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 문제점
 * - 하드코딩된 매핑 작업 매번 URI 조건 분기점을 만들어야 되는가?
 * - 파라미터 바인딩을 직접 해줘야 하는가?
 * -
 */
@SpringBootApplication
public class TobySpringApplication {

    public static void main(String[] args) {
        // onRefresh
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                ServletWebServerFactory webServer = new TomcatServletWebServerFactory();
                webServer.getWebServer(servletContext -> servletContext.addServlet("dispatcherServlet",
                        new DispatcherServlet(this)
                ).addMapping("/*")).start();
            }
        };

        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);

        applicationContext.refresh();
    }
}
