package com.example.backend.auth.services.impl;

import com.example.backend.auth.entities.Role;
import com.example.backend.auth.mappers.RoleMapper;
import com.example.backend.auth.models.RoleModel;
import com.example.backend.auth.repositories.RoleRepository;
import com.example.backend.auth.services.RoleService;
import com.example.commons.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public List<RoleModel> getAll() {
        return roleRepository.findAll().stream().map(roleMapper::toModel).toList();
    }

    @Override
    public RoleModel save(RoleModel roleModel) {
        Role savedRole = roleRepository.save(roleMapper.toEntity(roleModel));
        return roleMapper.toModel(savedRole);
    }

    @Override
    public RoleModel update(RoleModel roleModel) {
        if (roleModel.getId() == null)
            throw new IllegalArgumentException("ID is required for update operation");

        return this.save(roleModel);
    }

    @Override
    public boolean deleteById(Long id) throws NotFoundException {
        roleRepository.delete(
                roleRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(String.format("No role found with id '%s'.", id)))
        );
        return true;
    }
}
