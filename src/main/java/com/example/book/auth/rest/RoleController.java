package com.example.book.auth.rest;

import com.example.book.auth.models.RoleModel;
import com.example.book.auth.services.RoleService;
import com.example.book.support.BaseController;
import com.example.book.support.exceptions.NotFoundException;
import com.example.book.support.models.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController extends BaseController {
    private final RoleService roleService;

    @GetMapping
    public ApiResponse<List<RoleModel>> getAll(){
        return successResponse(roleService.getAll());
    }

    @PostMapping
    public ApiResponse<RoleModel> save(@RequestBody RoleModel roleModel) {
        return successResponse(roleService.save(roleModel));
    }

    @PutMapping
    public ApiResponse<RoleModel> update(@RequestBody RoleModel roleModel) {
        return successResponse(roleService.update(roleModel));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Boolean> deleteById(@PathVariable Long id) throws NotFoundException {
        return successResponse(roleService.deleteById(id));
    }
}
