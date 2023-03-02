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
 *  - 프론트 컨트롤러의 역할 처럼 공통적인 부분을 한번에 해결할 수 있다.
 *  - 스프링 컨테이너와 연결하는데 특화되어있다.
 *  - DispatchServlet을 이용하기 위해서는 스프링 컨테이너 구현체를 변경해야 한다.
 *      -applicationContext -> WebApplicationContext 의존성 객체가 변경됨
 *
 *
 * <스프링 컨테이너로 통합>
 * 서블릿 컨테이너를 만들고  서블릿을 초기화하는 작업을 스프링 컨테이너가 초기화되는 과정에서 일어나도록 한다.
 *  -> 스프링부트에서 현재 이렇게 사용하고 있음.
 *      - 템플릿 메소드: 상속을 이용하여 구현한다.
 *      - 훅 메소드: 추상 클래스에 들어있는, 아무 일도 하지 않거나 기본 행동을 정의하는 메소드로, 서브 클래스에서 오버라이드 할 수 있습니다.
 *      - 정리 : GenericWebApplicationContext 에서 onRefresh()  훅 메소드를 구현한다.
 *          - 서블릿 컨테이너와 서블릿초기화하는 작업을 스프링 컨테이너가 초기화 되는 과정에 넣는 작업 진행
 */

public class TobySpringApplication {
    public static void main(String[] args) {
        /**
         * 스프링 컨테이너 생성
         * 컨테이너에 빈 등록
         * 컨테이너 Refresh()
         */
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext(){
            @Override
            protected void onRefresh() {
                super.onRefresh();
                TomcatServletWebServerFactory webServerFactory = new TomcatServletWebServerFactory();
                WebServer webServer = webServerFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("hello", new DispatcherServlet(this)).addMapping("/*");
                });
                webServer.start();
            }
        };
        applicationContext.registerBean(RestHelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh();
    }
}
