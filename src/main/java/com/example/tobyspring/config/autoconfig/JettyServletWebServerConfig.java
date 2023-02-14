package com.example.tobyspring.config.autoconfig;

import com.example.tobyspring.config.ConditionalMyOnClass;
import com.example.tobyspring.config.MyAutoConfiguration;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@MyAutoConfiguration
//@Conditional(JettyServletWebServerConfig.JettyCondition.class)
@ConditionalMyOnClass("org.eclipse.jetty.server.Server")
public class JettyServletWebServerConfig {
    @Bean("jettyWebServerFactory")
    public ServletWebServerFactory servletWebServerFactory() {
        return new JettyServletWebServerFactory();
    }

//    static class JettyCondition implements Condition {
//
//        @Override
//        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//
//
//            return ClassUtils.isPresent("org.eclipse.jetty.server.Server", context.getClassLoader());
//        }
//    }
}
