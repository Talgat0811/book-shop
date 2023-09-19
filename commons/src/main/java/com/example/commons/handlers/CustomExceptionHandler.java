package com.example.commons.handlers;

import com.example.commons.models.ApiResponse;
import com.example.commons.rest.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends BaseController {

    @ExceptionHandler(Exception.class)
    public ApiResponse<String> handleAnyException(Exception exception) {
        log.error(exception.getMessage());
        return exceptionResponse(exception.getMessage());
    }
}
