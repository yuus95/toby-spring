package com.example.tobyspring.config.autoconfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 빈 포스트 프로세서 - 빈 후처리기
 *
 */
@Component
@MyConfigurationProperties
@Getter
@Setter
public class ServerProperties {
    @Value("${contextPath}")
    private String contextPath;

    @Value("${port:8080}")
    private int port;
}
