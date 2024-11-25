package com.example.shop55_be.service.impl;

import com.example.shop55_be.model.User;
import com.example.shop55_be.reposistory.UserRepo;
import com.example.shop55_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }

    @Override
    public User findByCode(String code) {
        return userRepo.findByCode(code).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return userRepo.getAllUser();
    }
}
