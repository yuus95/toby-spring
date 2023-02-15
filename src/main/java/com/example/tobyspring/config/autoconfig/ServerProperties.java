package com.example.tobyspring.config.autoconfig;

import lombok.Getter;
import lombok.Setter;

/**
 * 빈 포스트 프로세서 - 빈 후처리기
 *
 @Getter
 @Setter */

@Getter
@Setter
@MyConfigurationProperties(prefix = "server")
public class ServerProperties {
//    @Value("${contextPath}")
    private String contextPath;

//    @Value("${port:8080}")
    private int port;
}

