package com.example.tobyspring.config.autoconfig;

import com.example.tobyspring.config.MyConfigurationProperties;

/**
 * 빈 포스트 프로세서 - 빈 후처리기
 *
 @Getter
 @Setter */


@MyConfigurationProperties(prefix = "server")
public class ServerProperties {
//    @Value("${contextPath}")
    private String contextPath;

//    @Value("${port:8080}")
    private int port;

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}

