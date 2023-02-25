package com.example.tobyspring;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HTTPie
 * curl
 * <p>
 * Response
 * - 응답갓
 * - contentType
 * - 내용
 */

public class TobySpringApplication {
    public static void main(String[] args) {
        TomcatServletWebServerFactory webServerFactory = new TomcatServletWebServerFactory();
        WebServer webServer = webServerFactory.getWebServer(servletContext -> {
            RestHelloController helloController = new RestHelloController();
            servletContext.addServlet("hello", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

                    // 프론트 컨트롤러가 필요한 이유는 공통적으로 필요한 부분을 한번에 해결할 수 있기 떄문이다.
                    //  - 인증 보안, 인가
                    if(req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
                        String name = req.getParameter("name");

                        // 컨트롤러단에는 자바 객체로만 전달된다 웹 기술은 서블릿에서 처리를 해준다.
                        String result = helloController.hello(name);

                        // 반환 작업일 때도 자바객체로 받고 웹 기술은 서블릿에서 활용한다.
                        resp.setStatus(200);
                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                        resp.getWriter().println(result);
                    }
                    else{
                        resp.setStatus(404);
                    }

                }
            }).addMapping("/*");
        });

        webServer.start();
    }
}
