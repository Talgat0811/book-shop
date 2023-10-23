package com.example.backend.support.factories;

import com.example.backend.support.annotations.TypedValidator;
import com.example.backend.support.enums.ValidatorType;
import com.example.commons.utils.BaseValidator;
import lombok.extern.slf4j.Slf4j;

import org.reflections.Reflections;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.EnumMap;
import java.util.Set;

@Slf4j
@Component
public class ValidatorsFactory {
    private final EnumMap<ValidatorType, Class<? extends BaseValidator>> validatorsMap;
    private final ApplicationContext applicationContext;

    public ValidatorsFactory(Reflections reflectionsComponent, ApplicationContext applicationContext) {
        EnumMap<ValidatorType, Class<? extends BaseValidator>> validators = new EnumMap<>(ValidatorType.class);

        Set<Class<?>> validatorClasses = reflectionsComponent.getTypesAnnotatedWith(TypedValidator.class);
        for (Class<?> validatorClass : validatorClasses) {
            TypedValidator predicateAnnotation = validatorClass.getAnnotation(TypedValidator.class);
            if (!validators.containsKey(predicateAnnotation.value())) {
                validators.put(predicateAnnotation.value(), (Class<? extends BaseValidator>) validatorClass);
                log.info("Validator type {} has been registered",  predicateAnnotation.value());
            } else {
                throw new BeanCreationException(MessageFormat.format(
                        "Multiple definitions of PredicateType for {0} found", predicateAnnotation.value())
                );
            }
        }

        this.validatorsMap = validators;
        this.applicationContext = applicationContext;
    }

    public BaseValidator getValidator(ValidatorType validatorType) {
        if (validatorType == null) throw new UnsupportedOperationException("validatorType is required");

        if (!validatorsMap.containsKey(validatorType)) {
            throw new UnsupportedOperationException(MessageFormat.format("Validator not found for {0}", validatorType));
        }

        return applicationContext.getBean(this.validatorsMap.get(validatorType));
    }
}
