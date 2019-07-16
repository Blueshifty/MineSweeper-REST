package com.example.openshift_deneme.config;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.openshift_deneme.json.JsonRequestBodyArgumentResolverRegisteringBeanPostProcessor;

@Configuration
public class JsonRequestBodyConfig {

    @Bean
    static BeanPostProcessor jsonRequestBodyArgumentResolverRegisteringBeanPostProcessor() {
        return new JsonRequestBodyArgumentResolverRegisteringBeanPostProcessor();
    }
}
