package com.example.tobyspring.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
//외부 패키지에 있는 Configuration들을 임포트할 수 있다.
@Import(MyAutoConfigImportSelector.class)
public @interface EnableAutoConfiguration {
}
