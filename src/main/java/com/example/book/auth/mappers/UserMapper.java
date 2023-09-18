package com.example.book.auth.mappers;

import com.example.book.auth.entities.User;
import com.example.book.auth.models.UserModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = RoleMapper.class)
public interface UserMapper {

    @Mapping(target = "role", source = "roleModel")
    User toEntity(UserModel model);

    @InheritInverseConfiguration
    UserModel toModel(User entity);
}
