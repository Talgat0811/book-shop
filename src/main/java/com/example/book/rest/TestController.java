package com.example.book.rest;

import com.example.book.support.BaseController;
import com.example.book.support.models.ApiResponse;
import com.example.book.support.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController extends BaseController {

    @GetMapping("/get-current-user")
    public ApiResponse<Authentication> getCurrentUser(){
        return successResponse(SecurityUtils.getCurrentUser());
    }
}
