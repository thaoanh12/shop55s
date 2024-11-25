package com.example.shop55_be.service.impl;

import com.example.shop55_be.entity.Role;
import com.example.shop55_be.model.ERole;
import com.example.shop55_be.reposistory.RoleRepo;
import com.example.shop55_be.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepo roleRepo;


    @Override
    public Role findById(long id) {
        return roleRepo.findById(id).orElse(null);
    }

    @Override
    public Role findByRoleName(ERole roleName) {
        return roleRepo.findByRoleName(roleName);
    }
}
