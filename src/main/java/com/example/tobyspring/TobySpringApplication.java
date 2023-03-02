package com.example.tobyspring;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 스프링 컨테이너를 생성하여 우리가 직접 컨트롤러를 생성하는 것이 아니라 컨테이너에게 요청을 해서 만들어준 컨트롤러를 가져와서 사용할 수 있도록 한다.
 * 스프링 컨테이너는 어셈블러로서 객체들에게 필요한 의존성들을 주입할 수 있도록 도와준다.
 * - 팩토리 메서드를 활용하여 빈 객체들을 생성하여 직접 주입할 수 있다,
 * - 생성자 주입 방식을 이용하여 빈 객체가 생성될 때 주입해줄 수도 잇다.
 * <p>
 * <p>
 * 스프링 컨테이너와 연결하는데 특화가 되어있는 DispatcherServlet 이라는 개념이 도입됨
 * - 프론트 컨트롤러의 역할 처럼 공통적인 부분을 한번에 해결할 수 있다.
 * - 스프링 컨테이너와 연결하는데 특화되어있다.
 * - DispatchServlet을 이용하기 위해서는 스프링 컨테이너 구현체를 변경해야 한다.
 * -applicationContext -> WebApplicationContext 의존성 객체가 변경됨
 * <p>
 * <p>
 * <스프링 컨테이너로 통합>
 * 서블릿 컨테이너를 만들고  서블릿을 초기화하는 작업을 스프링 컨테이너가 초기화되는 과정에서 일어나도록 한다.
 * -> 스프링부트에서 현재 이렇게 사용하고 있음.
 * - 템플릿 메소드: 상속을 이용하여 구현한다.
 * - 훅 메소드: 추상 클래스에 들어있는, 아무 일도 하지 않거나 기본 행동을 정의하는 메소드로, 서브 클래스에서 오버라이드 할 수 있습니다.
 * - 정리 : GenericWebApplicationContext 에서 onRefresh()  훅 메소드를 구현한다.
 * - 서블릿 컨테이너와 서블릿초기화하는 작업을 스프링 컨테이너가 초기화 되는 과정에 넣는 작업 진행
 * <p>
 * 팩토리 메소드를 활용하여 빈 오브젝트를 생성한다.
 * 스프링 컨테이너에 있는 빈을 또다른 빈에서 사용하고 있다면 어떻게 주입할 것인지 에대한 구성정보를 설정해줘야 한다.
 * 이 구성정보를 제공하는 방법으로 외부 설정파일 적용 방법도 있다.
 * 팩토리 메서드를 이용하여 의존성 주입도 다하고 빈을 다 생성한다.
 * 어떨 경우에는 주입을 직접해줘야 하는데 자바코드로 이 작업을 진행한다면 이해하기 쉽게 풀어 쓸 수 있다.
 *
 * @Configuration 어노테이션과 @Bean어노테이션을 활용하면
 * 자바 코드로 스프링 컨테이너에 빈들을 생성할 수 있다.
 * GenericWebApplicationContext 은 어노테이션으과 자바 코드로 구성된 스프링 구성 정보를 읽어 올 수 없다.
 * GenericWebApplicationContext -> AnnotationConfigWebApplicationContext 구현체로 변경해야한다.
 * - register 메소드를 활용하여 자바 구성정보를 갖고 있는 클래스를 등록할 수 있다.
 * - Register one or more component classes to be processed.
 * @ComponentScan 매번 bean 객체를 개발자가 직접 팩토리 매서드를 이용하여 구현할 필요가 없다.
 * Bean 객체를 어노태이션을 이용하여 생성해줄 수 있는데 이 때 @Component 와 @ComponentScan을 활용한다.
 * @ComponseScan: Component 어노테이션이 붙은 클래스들을 빈 객체로 생성할 수 있다.
 * @Component: 어노테이션 기반 구성 클래스나, 클래스 패스들로 사용할 때  어노테이션 환경 자동구성으로 설정할 수 있게 해준다.
 * <p>
 * <p>
 * 스프링 컨테이너를 활용하면 DispatcherServlet 과 ServletContainer 를 빈객체로 등록하여 좀 더 간단하게 스프링 컨테이너를 초기화 할 수 있다.
 * @Configuration 해당 클래스에 어노테이션을 등록하고 Bean 객체로 DispatcherServlet과 ServletContainer를 생성해준다.
 */


@Configuration
@ComponentScan
public class TobySpringApplication {

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Bean
    public ServletWebServerFactory webServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    public static void main(String[] args) {
        /**
         * 스프링 컨테이너 생성
         * 컨테이너에 빈 등록
         * 컨테이너 Refresh()
         */
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                ServletWebServerFactory webServerFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);

                WebServer webServer = webServerFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("hello", new DispatcherServlet(this)).addMapping("/*");
                });
                webServer.start();
            }
        };
        applicationContext.register(TobySpringApplication.class);
        applicationContext.refresh();
    }
}
