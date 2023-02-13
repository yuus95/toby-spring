package com.example.tobyspring.config;

import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 빈 어노테이션을 포함한 객체가 또다른 객체를 생성하는 것이 아니라면
 * configuration.proxyBeanMethods() 를 false로 납둬도 상관없다. (굳이 의존성이 없는 객체를 일부러 프록시르 만들 필요가
 * 없기 때문이다. 프록시를 만들어서 나오는 이점이 없다.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Configuration(proxyBeanMethods = false)
public @interface MyAutoConfiguration {
}
/**
 * 우리가 직접 configuration 코드로 구성할 경우 에도 사용할 수 있다.
 */
