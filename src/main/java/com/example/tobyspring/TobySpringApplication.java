package com.example.tobyspring;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 스프링 컨테이너를 생성하여 우리가 직접 컨트롤러를 생성하는 것이 아니라 컨테이너에게 요청을 해서 만들어준 컨트롤러를 가져와서 사용할 수 있도록 한다.
 * 스프링 컨테이너는 어셈블러로서 객체들에게 필요한 의존성들을 주입할 수 있도록 도와준다.
 * - 팩토리 메서드를 활용하여 빈 객체들을 생성하여 직접 주입할 수 있다,
 * - 생성자 주입 방식을 이용하여 빈 객체가 생성될 때 주입해줄 수도 잇다.
 *
 */

public class TobySpringApplication {
    public static void main(String[] args) {
        /**
         * 스프링 컨테이너 생성
         * 컨테이너에 빈 등록
         * 컨테이너 Refresh()
         */
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.registerBean(RestHelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh();

        TomcatServletWebServerFactory webServerFactory = new TomcatServletWebServerFactory();
        WebServer webServer = webServerFactory.getWebServer(servletContext -> {
            servletContext.addServlet("hello", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

                    // 프론트 컨트롤러가 필요한 이유는 공통적으로 필요한 부분을 한번에 해결할 수 있기 떄문이다.
                    //  - 인증 보안, 인가
                    if(req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
                        String name = req.getParameter("name");
                        RestHelloController bean = applicationContext.getBean(RestHelloController.class);
                        String result = bean.hello(name);
                        // 컨트롤러단에는 자바 객체로만 전달된다 웹 기술은 서블릿에서 처리를 해준다.

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
