package com.example.tobyspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.Encoding;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 웹어플리케이셔 로직은 다른 컨트롤러한테 위임해야한다.
 *
 * 현재 스프링을 사용하지 않고 웹서비스를 띄우는 연습
 *
 * HttpServletRequest
 *  - gerRequestURI : 요청 URI 흭득
 *  - getMethod: 요청 HTTP 메소드 흭득
 *
 *   매핑과 바인딩
 *      - 매핑 : 웹 요청에 들어있는 정보를 이용해서 어떤 요청을 수행할 것인가 결정하는 요소
 *      - 바인딩: 웹 요청과 응답을 건드리는 오브젝트를 사용하지 않는다. 파라미터로 넘어온 데이터를 자바 클래스에 맞게 바인딩 해준다.
 */
public class TobySpringApplication {
    public static void main(String[] args) {
        ServletWebServerFactory webServer = new TomcatServletWebServerFactory();
        webServer.getWebServer(servletContext -> {
            servletContext.addServlet( "frontController", new HttpServlet() {
                final HelloController helloController = new HelloController();
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    String name = req.getParameter("name");
                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
                        resp.setStatus(200);
                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE);
                        resp.setHeader(HttpHeaders.ACCEPT_ENCODING, Encoding.DEFAULT_CHARSET.displayName());
                        resp.getWriter().println(helloController.getString(name));
                    }

                    resp.setStatus(200);
                    resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                    resp.getWriter().println("Text  " + name);
                }
            }).addMapping("/*");
        }).start();

    }
}
