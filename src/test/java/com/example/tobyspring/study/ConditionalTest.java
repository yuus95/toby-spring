package com.example.tobyspring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

public class ConditionalTest {
    @Test
    public void configTest() {
        // true
        // Test환경에서 사용할 수 있는 ApplicationContext
        // 웹 서블릿 환경이 없다.
        ApplicationContextRunner applicationContextRunner = new ApplicationContextRunner();
        applicationContextRunner.withUserConfiguration(Config1.class)
                .run(context -> {
                    Assertions.assertThat(context).hasSingleBean(MyBean.class);
                    Assertions.assertThat(context).hasSingleBean(Config1.class);
                });
//
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
//        applicationContext.register(Config1.class);
//        applicationContext.refresh();
//
//        MyBean bean = applicationContext.getBean(MyBean.class);
        /**
         * 예외를 잡는 테스트코드를 찾아야 한다.
         */
        //false
        new ApplicationContextRunner().withUserConfiguration(Config2.class)
                .run(context -> {
                    Assertions.assertThat(context).doesNotHaveBean(MyBean.class);
                    Assertions.assertThat(context).doesNotHaveBean(Config2.class);
                });

    }

    // BooleanCondition
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    public @interface BooleanConditional {
        boolean value();
    }

    // 메타 어노테이션 적용 방법

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(TrueCondition.class)
    public @interface TrueConditional {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(FalseCondition.class)
    public @interface FalseConditional {
    }

    @Configuration
//    @TrueConditional
    @BooleanConditional(true)
    static class Config1 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    @Configuration
//    @FalseConditional
    @BooleanConditional(false)
    static class Config2 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    static class MyBean {

    }

    static class FalseCondition implements Condition {

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return false;
        }
    }

    static class TrueCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }

    static class BooleanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(BooleanConditional.class.getName());
            return (Boolean) annotationAttributes.get("value");
        }
    }
}
