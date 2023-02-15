package com.example.tobyspring.config.autoconfig;

import lombok.Getter;
import lombok.Setter;

@MyConfigurationProperties(prefix = "data")
@Getter
@Setter
public class MyDataSourceProperties {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
}
