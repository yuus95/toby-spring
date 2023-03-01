package com.example.tobyspring;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 스프링 컨테이너를 생성하여 우리가 직접 컨트롤러를 생성하는 것이 아니라 컨테이너에게 요청을 해서 만들어준 컨트롤러를 가져와서 사용할 수 있도록 한다.
 * 스프링 컨테이너는 어셈블러로서 객체들에게 필요한 의존성들을 주입할 수 있도록 도와준다.
 * - 팩토리 메서드를 활용하여 빈 객체들을 생성하여 직접 주입할 수 있다,
 * - 생성자 주입 방식을 이용하여 빈 객체가 생성될 때 주입해줄 수도 잇다.
 *
 *
 * 스프링 컨테이너와 연결하는데 특화가 되어있는 DispatcherServlet 이라는 개념이 도입됨
 * 프론트 컨트롤러의 역할 처럼 공통적인 부분을 한번에 해결할 수 있다.
 * 스프링 컨테이너와 연결하는데 특화되어있다.
 *
 * applicationContext -> WebApplicationContext 의존성 객체가 변경됨
 */

public class TobySpringApplication {
    public static void main(String[] args) {
        /**
         * 스프링 컨테이너 생성
         * 컨테이너에 빈 등록
         * 컨테이너 Refresh()
         */
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
        applicationContext.registerBean(RestHelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh();

        TomcatServletWebServerFactory webServerFactory = new TomcatServletWebServerFactory();
        WebServer webServer = webServerFactory.getWebServer(servletContext -> {
            servletContext.addServlet("hello", new DispatcherServlet(applicationContext)).addMapping("/*");
        });

        webServer.start();
    }
}
