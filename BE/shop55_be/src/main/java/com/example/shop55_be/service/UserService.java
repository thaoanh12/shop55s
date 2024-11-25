package com.example.shop55_be.service;

import com.example.shop55_be.model.User;
import java.util.List;

public interface UserService {
    User findByEmail(String email);
    User findByCode(String code);
    List<User> getAll();
}
