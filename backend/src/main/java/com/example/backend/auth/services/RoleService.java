package com.example.backend.auth.services;

import com.example.backend.auth.models.RoleModel;
import com.example.commons.exceptions.NotFoundException;

import java.util.List;

public interface RoleService {

    List<RoleModel> getAll();

    RoleModel save(RoleModel roleModel);

    RoleModel update(RoleModel roleModel);

    boolean deleteById(Long id) throws NotFoundException;
}
