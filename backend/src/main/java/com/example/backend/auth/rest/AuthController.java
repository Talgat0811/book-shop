package com.example.backend.auth.rest;

import com.example.backend.auth.models.AuthUserRequest;
import com.example.backend.auth.models.AuthUserResponse;
import com.example.backend.auth.services.impl.AuthService;
import com.example.commons.models.ApiResponse;
import com.example.commons.rest.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController extends BaseController {
    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<AuthUserResponse> login(@RequestBody AuthUserRequest authUserRequest) {
        return successResponse(authService.login(authUserRequest.getLogin(), authUserRequest.getPassword()));
    }

}
