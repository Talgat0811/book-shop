package com.example.backend.support.annotations;

import com.example.backend.support.enums.ValidatorType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TypedValidator {
    ValidatorType value();
    int paramIndex() default 0;
}
