package com.example.tobyspring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigurationTest {

    @Test
    public void AnnotationContext() {
        //given
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //when
        // refresh() must be called in order for the context to fully process the new classes. -register 등록 시 필수
        applicationContext.register(MyConfig.class);
        applicationContext.refresh();
        Bean1 bean1 = applicationContext.getBean(Bean1.class);
        Bean2 bean2 = applicationContext.getBean(Bean2.class);
        //then
        Assertions.assertThat(bean1.common).isEqualTo(bean2.common);
    }

    @Test
    public void configuration() {
        //given
        MyConfig myConfig = new MyConfigProxy();
        //when
        Bean1 bean1 = myConfig.bean1();
        Bean2 bean2 = myConfig.bean2();
        //then
        Assertions.assertThat(bean1.common).isSameAs(bean2.common);
    }

    // 스프링의 프록시 객체 예시
    static class MyConfigProxy extends MyConfig {
        private Common common;

        @Override
        Common common() {
            if (this.common == null) {
                this.common = super.common();
            }
            return this.common;
        }

        @Override
        Bean1 bean1() {
            return new Bean1(this.common);
        }

        @Override
        Bean2 bean2() {
            return new Bean2(this.common);
        }
    }

    @Configuration
    static class MyConfig {
        @Bean
        Common common() {
            return new Common();
        }

        @Bean
        Bean1 bean1() {
            return new Bean1(common());
        }

        @Bean
        Bean2 bean2() {
            return new Bean2(common());
        }
    }

    static class Bean1 {
        private final Common common;

        public Bean1(Common common) {
            this.common = common;
        }
    }

    static class Bean2 {
        private final Common common;

        public Bean2(Common common) {
            this.common = common;
        }
    }

    static class Common {
    }
    // Bean1 <-- Common
    // Bean2 <-- Common


}
