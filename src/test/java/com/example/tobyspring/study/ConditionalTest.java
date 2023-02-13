package com.example.tobyspring.study;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ConditionalTest {
    @Test
    public void configTest() {
        //true
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Config1.class);
        applicationContext.refresh();

        Config1 bean = applicationContext.getBean(Config1.class);
        //false
    }

    @Configuration
    @Conditional(Config1.Config1Condition.class)
    static class Config1 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }

        static class Config1Condition implements Condition {

            @Override
            public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
                return false;
            }
        }
    }

    @Configuration
    @Conditional(Config2.Config2Condition.class)
    static class Config2 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }

        static class Config2Condition implements Condition {
            @Override
            public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
                return true;
            }
        }
    }

    static class MyBean {

    }
}
