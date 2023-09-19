package com.example.backend.auth.mappers;

import com.example.backend.auth.entities.Permission;
import com.example.backend.auth.models.PermissionModel;
import org.mapstruct.Mapper;

@Mapper
public interface PermissionMapper {

    Permission toEntity(PermissionModel model);

    PermissionModel toModel(Permission entity);

}
