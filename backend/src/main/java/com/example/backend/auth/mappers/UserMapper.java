package com.example.backend.auth.mappers;

import com.example.backend.auth.entities.User;
import com.example.backend.auth.models.UserModel;
import com.example.backend.auth.repositories.RoleRepository;
import com.example.commons.exceptions.NotFoundException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class UserMapper {
    @Autowired protected RoleRepository roleRepository;

    @Mapping(target = "role", expression = "java( roleRepository.findByName(model.getRoleName())" +
            ".orElseThrow(() -> new com.example.commons.exceptions.NotFoundException(String.format(\"no role found with name '%s'\", model.getRoleName()))) )")
    public abstract User toEntity(UserModel model) throws NotFoundException;

    @Mapping(target = "roleName", source = "role.name")
    public abstract UserModel toModel(User entity);
}
