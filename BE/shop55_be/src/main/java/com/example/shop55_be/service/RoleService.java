package com.example.shop55_be.service;

import com.example.shop55_be.entity.Role;
import com.example.shop55_be.model.ERole;

public interface RoleService {
    Role findById(long id);
    Role findByRoleName(ERole roleName);
}
