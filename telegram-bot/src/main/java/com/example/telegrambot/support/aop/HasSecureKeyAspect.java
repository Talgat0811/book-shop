package com.example.telegrambot.support.aop;


import com.example.telegrambot.configs.properties.SecureDataProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class HasSecureKeyAspect {
    private final SecureDataProperties secureDataProperties;

    @Around("execution(* *(..)) && @within(com.example.telegrambot.support.annotations.HasSecureKey))")
    public Object authenticate(ProceedingJoinPoint proceedingJoinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        return Optional.ofNullable(request.getHeader(secureDataProperties.getKeyName()))
                .map(header -> {
                    try {
                        return header.equals(secureDataProperties.getKeyValue()) ?
                                proceedingJoinPoint.proceed() :
                                ResponseEntity.internalServerError().body("Authentication header value is not valid");
                    } catch (Throwable e) {
                        return ResponseEntity.internalServerError().body(e.getMessage());
                    }
                })
                .orElse(ResponseEntity.internalServerError().body("Authentication header is not valid"));
    }
}
