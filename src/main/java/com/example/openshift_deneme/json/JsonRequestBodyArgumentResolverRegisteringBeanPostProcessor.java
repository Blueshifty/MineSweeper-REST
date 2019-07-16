package com.example.openshift_deneme.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.List;

public class JsonRequestBodyArgumentResolverRegisteringBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof RequestMappingHandlerAdapter) {

            RequestMappingHandlerAdapter handlerAdapter = (RequestMappingHandlerAdapter) bean;
            List<HandlerMethodArgumentResolver> argumentResolvers = handlerAdapter.getArgumentResolvers();
            List<HandlerMethodArgumentResolver> extendedArgumentResolverList = new ArrayList<>();

            if (argumentResolvers != null) {

                for (HandlerMethodArgumentResolver argumentResolver : argumentResolvers) {

                    if (argumentResolver instanceof RequestResponseBodyMethodProcessor) {

                        ObjectMapper objectMapper = new ObjectMapper();
                        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                        extendedArgumentResolverList.add(
                                new JsonRequestBodyArgumentResolver(
                                        (RequestResponseBodyMethodProcessor)argumentResolver, objectMapper));
                    } else {
                        extendedArgumentResolverList.add(argumentResolver);
                    }
                }

                handlerAdapter.setArgumentResolvers(extendedArgumentResolverList);

                return handlerAdapter;
            }
        }

        return bean;
    }

    /**
     * Do not process beans in any special way before initialization.
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
