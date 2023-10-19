package com.example.backend.support.middlewares.aspects;

import com.example.backend.support.annotations.TypedValidator;
import com.example.backend.support.factories.ValidatorsFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ValidationAspect {
    private final ValidatorsFactory validatorsFactory;

    @Around("@annotation(typedValidator)")
    public Object process(ProceedingJoinPoint proceedingJoinPoint, TypedValidator typedValidator) throws Throwable {
        validatorsFactory.getPredicate(typedValidator.value())
                .verify(proceedingJoinPoint.getArgs()[typedValidator.paramIndex()]);

        return proceedingJoinPoint.proceed();
    }

}
