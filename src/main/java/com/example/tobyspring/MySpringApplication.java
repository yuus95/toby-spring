package com.example.tobyspring;

import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Component
public class MySpringApplication {
    public static void run(Class<?> mainClass, String[] args) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
                ServletWebServerFactory webServer = this.getBean(ServletWebServerFactory.class);


                webServer.getWebServer(servletContext -> servletContext
                        .addServlet("dispatcherServlet", dispatcherServlet)
                        .addMapping("/*")
                ).start();
            }
        };

        // Register one or more component classes to be processed.
        applicationContext.register(mainClass);
        applicationContext.refresh();
    }
}
