package com.example.tobyspring.config.autoconfig;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@AutoConfiguration
public class ServerPropertiesConfig {

    /**
     * Environment
     * Interface representing the environment in which the current application is running
     */
    @Bean
    public ServerProperties serverProperties(Environment enviroment) {
        return  Binder.get(enviroment).bind("",ServerProperties.class).get();
    }
}
