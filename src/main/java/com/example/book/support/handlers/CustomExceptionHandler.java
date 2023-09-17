package com.example.book.support.handlers;

import com.example.book.support.BaseController;
import com.example.book.support.models.ApiResponse;
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
