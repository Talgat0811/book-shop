package com.example.commons.rest;

import com.example.commons.enums.ResultCode;
import com.example.commons.models.ApiResponse;

public abstract class BaseController {

    protected <T> ApiResponse<T> successResponse(T result) {
        return new ApiResponse<>(result, ResultCode.SUCCESS);
    }

    protected ApiResponse<String> exceptionResponse(String details) {
        return new ApiResponse<>(null, ResultCode.EXCEPTION, details);
    }
}
