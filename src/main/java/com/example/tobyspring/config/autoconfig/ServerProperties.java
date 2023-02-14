package com.example.tobyspring.config.autoconfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class ServerProperties {
    @Value("${contextPath}")
    private String contextPath;

    @Value("${port:8080}")
    private int port;
}
