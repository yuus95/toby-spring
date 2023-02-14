package com.example.tobyspring.config.autoconfig;

import com.example.tobyspring.config.ConditionalMyOnClass;
import com.example.tobyspring.config.MyAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * 학습용 테스트를 이용해서 공부하면 좀 더 이해하기 쉽다.
 */
@MyAutoConfiguration
//@Conditional(TomcatServletWebServerConfig.TomcatCondition.class)
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatServletWebServerConfig {
    @Bean("tomcatWebServerFactory")
    // 관련된 빈이 미리 생성되어 있다면 현재 메소드에서 생성하는 빈 로직은 무시된다.
    // 생서되어 있지 않다면 현재 메소드를 이용하여 해당 빈을 생성한다.
    @ConditionalOnMissingBean
    public ServletWebServerFactory servletWebServerFactory(Environment env) {
        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
        String contextPath = env.getProperty("contextPath");
        System.out.println("contextPath  " + contextPath);
        tomcatServletWebServerFactory.setContextPath(env.getProperty("contextPath"));
        return tomcatServletWebServerFactory;
    }

//    static class TomcatCondition implements Condition {
//
//        @Override
//        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//            return ClassUtils.isPresent("org.apache.catalina.startup.Tomcat", context.getClassLoader());
//        }
//    }
}
