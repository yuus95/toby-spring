package com.example.tobyspring;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;

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
        WebServer webServer = webServerFactory.getWebServer(servletContext -> servletContext.addServlet("hello", new HttpServlet() {
            @Override
            protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                System.out.println("Hello URL Active");
                resp.setStatus(200);
                resp.setHeader("content-type","text");
                resp.getWriter().println("Hello Servlet");
            }
        }).addMapping("/hello"));

        webServer.start();
    }
}
