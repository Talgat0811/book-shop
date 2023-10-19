package com.example.backend.support.configs;

import org.reflections.Reflections;
import org.reflections.scanners.*;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReflectionsConfig {

    @Bean
    public Reflections reflectionsComponent() {
        return new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("com.example.backend"))
                .setScanners(
                        new FieldAnnotationsScanner(),
                        new MethodParameterScanner(),
                        new MethodParameterNamesScanner(),
                        new TypeElementsScanner(),
                        new TypeAnnotationsScanner(),
                        new SubTypesScanner(),
                        new MethodAnnotationsScanner()
                )
        );
    }

}
