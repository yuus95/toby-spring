package com.example.tobyspring;


import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@SpringBootApplication
public class TobySpringApplication {

    public static void main(String[] args) {

        // 웹서버를 만들기위한 팩토리
        ServletWebServerFactory webServerFactory = new TomcatServletWebServerFactory();

        WebServer webServer = webServerFactory.getWebServer(servletContext -> {

            // 서블릿 컨텍스트에서 사용할 서블릿을 등록
            servletContext.addServlet("hello", new HttpServlet() {

                //Receives standard HTTP requests from the public service method
                // http 요청을 받고 doMethod에 보냄
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//                    super.service(req, resp);

                    /**
                     * 응답 3대요소 체크
                     *  - 상태 코드, 헤더, 바디
                     */
                    resp.setStatus(200);
                    resp.setHeader("test-Type", "asdf/plain");
                    resp.getWriter().println("zz Test");
                }
            }).addMapping("/test");
        });
        webServer.start();
//        SpringApplication.run(TobySpringApplication.class, args);
    }

}
