package com.example.openshiftDeneme.json;

import java.lang.annotation.*;

/**
 Denotes a Spring MVC Controller handler method parameter
 that will be validated against JSON schema.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface JsonSchemaValidate {

    /**
     * Provide an explicit path to JSON schema that will be used
     * for validation instead of the conventional one.
     */
    String schemaPath() default "";
}
