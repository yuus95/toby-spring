package com.example.tobyspring;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


/**
 * 컴포넌트 어노테이션을 활용하면 편리하게 빈을 등록할 수 있다.
 * 문제는 컴포넌트만을 활용하여 빈을 등록하면 어떠한 빈이 등록되었는지 파악하기 어렵다.
 * 어
 * 컴포넌트 어노테이션을 가지고 있는 하위의 어노테이션을 사용하여 레이어를 구분할 수 있다.
 *
 */
@ComponentScan
public class TobySpringApplication {
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
