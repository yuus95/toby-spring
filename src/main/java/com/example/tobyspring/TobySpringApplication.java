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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 독립 실행형 스프링 어플리케이션
 * 서블릿 기능을 신경쓰지 않고 어떻게 개발하는지 파악하기
 * 각각의 컨트롤러를 스프링 컨테이너에서 사용하기
 * - 프론트 컨트롤러에서 직접 컨트롤러를 생산하지 않아도 된다.
 * <p>
 * 스프링 컨테이너는 오브젝트를 딱 한번만 만든다.
 * - SingleTon 과 비슷한 형태를 갖는다.
 * - 컨트롤러는 웹 컨트롤러 이기 떄문에 웹을 통해 들어온 데이터를 검증하고 비즈니스 로직 객체한테 데이터를 요청하여 받은 뒤 반환한다.
 *
 * Dependency - Injection
 *  -
 */
@SpringBootApplication
public class TobySpringApplication {

    public static void main(String[] args) {
        GenericApplicationContext applicationContext = new GenericApplicationContext();

        // 빈으로 등록하면  스프링 컨테이너에 등록된다.
        // 빈으로 등록된 클래스들을 어덯게 의존성을 채워줄까?
        //      -  컨테이너에서 찾아서 등록해준다.
        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh();

        ServletWebServerFactory webServer = new TomcatServletWebServerFactory();
        webServer.getWebServer(servletContext -> {
            servletContext.addServlet("frontController", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    String name = req.getParameter("name");
                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {

                        // 스프링 컨테이너가 가지고 있는 오브젝트 호출
                        HelloController helloController = applicationContext.getBean(HelloController.class);

                        resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
                        resp.getWriter().println(helloController.getString(name));
                    } else {
                        resp.getWriter().println("Text  " + name);
                    }
                }
            }).addMapping("/*");
        }).start();

    }
}
