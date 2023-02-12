package com.example.tobyspring.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{
                "com/example/tobyspring/config/autoconfig/DispatcherConfig",
                "com/example/tobyspring/config/autoconfig/ServletWebServerConfig"
        };
    }
}
