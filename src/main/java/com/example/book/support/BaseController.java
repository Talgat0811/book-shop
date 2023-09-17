package com.example.book.support;

import com.example.book.support.enums.ResultCode;
import com.example.book.support.models.ApiResponse;

public abstract class BaseController {

    protected <T> ApiResponse<T> successResponse(T result) {
        return new ApiResponse<>(result, ResultCode.SUCCESS);
    }

    protected ApiResponse<String> exceptionResponse(String details) {
        return new ApiResponse<>(null, ResultCode.EXCEPTION, details);
    }
}
