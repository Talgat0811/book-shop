package com.example.backend.auth.repositories;

import com.example.backend.auth.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
