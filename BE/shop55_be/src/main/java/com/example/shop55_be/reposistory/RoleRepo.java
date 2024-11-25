package com.example.shop55_be.reposistory;

import com.example.shop55_be.entity.Role;
import com.example.shop55_be.model.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByRoleName(ERole roleName);
}
