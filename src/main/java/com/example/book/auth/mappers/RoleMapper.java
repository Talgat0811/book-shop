package com.example.book.auth.mappers;

import com.example.book.auth.entities.Role;
import com.example.book.auth.models.RoleModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = PermissionMapper.class)
public interface RoleMapper {

    @Mapping(target = "permissions", source = "permissionModels")
    Role toEntity(RoleModel model);

    @InheritInverseConfiguration
    RoleModel toModel(Role entity);
}
