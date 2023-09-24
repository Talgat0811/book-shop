package com.example.backend.auth.rest;

import com.example.commons.models.FilterRequest;
import com.example.backend.auth.models.UserModel;
import com.example.backend.auth.services.UserService;
import com.example.commons.exceptions.NotFoundException;
import com.example.commons.models.ApiResponse;
import com.example.commons.rest.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController extends BaseController {
    private final UserService userService;

    @PostMapping("/filter")
    public ApiResponse<Page<UserModel>> getAll(@RequestBody FilterRequest filterRequest, Pageable pageable) {
        return successResponse(userService.getAll(filterRequest, pageable));
    }

    @PostMapping
    public ApiResponse<UserModel> save(@RequestBody UserModel userModel) {
        return successResponse(userService.save(userModel));
    }

    @PutMapping
    public ApiResponse<UserModel> update(@RequestBody UserModel userModel) {
        return successResponse(userService.update(userModel));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Boolean> deleteById(@PathVariable Long id) throws NotFoundException {
        return successResponse(userService.deleteById(id));
    }
}
