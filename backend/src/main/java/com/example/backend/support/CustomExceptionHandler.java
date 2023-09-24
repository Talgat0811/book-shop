package com.example.backend.support;

import com.example.commons.models.ApiResponse;
import com.example.commons.rest.BaseController;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionHandler extends BaseController {
    private final Map<Class<? extends Throwable>, HttpStatus> statusMap = new HashMap<>();

    @PostConstruct
    public void init() {
        statusMap.put(AuthenticationException.class, HttpStatus.UNAUTHORIZED);
        statusMap.put(AccessDeniedException.class, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse<String>> handleExceptions(Exception ex) {
        HttpStatus status = statusMap.keySet()
                .stream()
                .filter(key -> key.isAssignableFrom(ex.getClass()))
                .map(statusMap::get)
                .findFirst()
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR);

        log.error(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse(ex.getMessage()), status);
    }
}
