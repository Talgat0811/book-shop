package com.example.book.auth.mappers;

import com.example.book.auth.entities.Permission;
import com.example.book.auth.models.PermissionModel;
import org.mapstruct.Mapper;

@Mapper
public interface PermissionMapper {

    Permission toEntity(PermissionModel model);

    PermissionModel toModel(Permission entity);

}
