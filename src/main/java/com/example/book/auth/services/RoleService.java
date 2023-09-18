package com.example.book.auth.services;

import com.example.book.auth.models.RoleModel;
import com.example.book.support.exceptions.NotFoundException;

import java.util.List;

public interface RoleService {

    List<RoleModel> getAll();

    RoleModel save(RoleModel roleModel);

    RoleModel update(RoleModel roleModel);

    boolean deleteById(Long id) throws NotFoundException;
}
