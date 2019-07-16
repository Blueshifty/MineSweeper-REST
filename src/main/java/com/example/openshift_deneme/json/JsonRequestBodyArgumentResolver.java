package com.example.openshift_deneme.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JsonRequestBodyArgumentResolver implements HandlerMethodArgumentResolver {

    private RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor;

    private ObjectMapper objectMapper;

    public JsonRequestBodyArgumentResolver(RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor,
                                           ObjectMapper objectMapper) {
        this.requestResponseBodyMethodProcessor = requestResponseBodyMethodProcessor;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JsonSchemaValidate.class) ||
                parameter.hasParameterAnnotation(RequestBody.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        JsonSchemaValidate jsonSchemaValidateAnnotation =
                methodParameter.getParameterAnnotation(JsonSchemaValidate.class);

        if (jsonSchemaValidateAnnotation != null) {

            ClassPathResource schemaResource;
            String schemaPath = jsonSchemaValidateAnnotation.schemaPath();

            if (!schemaPath.isEmpty()) {
                schemaResource = new ClassPathResource(schemaPath);

            } else {
                schemaResource = new ClassPathResource("schema/" + methodParameter.getParameterType()
                        .getSimpleName().toLowerCase() + ".json");
            }

            try {
                HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
                String requestBodyJson = StreamUtils.copyToString(httpServletRequest.getInputStream(),
                        StandardCharsets.UTF_8);

                validate(requestBodyJson, schemaResource);

                return objectMapper.readValue(requestBodyJson, methodParameter.getParameterType());

            } catch (IOException e) {
                throw new UnavailableJsonSchemaException(e.getMessage(), e);
            }
        }

        return requestResponseBodyMethodProcessor.resolveArgument(methodParameter, mavContainer,
                webRequest, binderFactory);
    }

    private void validate(String srcJsonStr, ClassPathResource schemaResource) throws JsonSchemaException {

        try {

            JSONObject srcSchemaFileJson = new JSONObject(new JSONTokener(schemaResource.getInputStream()));

            SchemaLoader
                    .load(srcSchemaFileJson)
                    .validate(new JSONObject(srcJsonStr));

        } catch (ValidationException e) {
            throw new JsonSchemaValidationException(e.getErrorMessage(), e.toJSON());

        } catch (IOException e) {
            throw new JsonSchemaException(e.getMessage(), e);
        }
    }
}
