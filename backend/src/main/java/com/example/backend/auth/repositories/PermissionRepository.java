package com.example.backend.auth.repositories;

import com.example.backend.auth.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
