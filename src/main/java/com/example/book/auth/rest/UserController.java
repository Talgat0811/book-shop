package com.example.book.auth.rest;

import com.example.book.auth.models.FilterRequest;
import com.example.book.auth.models.UserModel;
import com.example.book.auth.services.UserService;
import com.example.book.support.BaseController;
import com.example.book.support.exceptions.NotFoundException;
import com.example.book.support.models.ApiResponse;
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
