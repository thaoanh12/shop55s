package com.example.shop55_be.service;

import com.example.shop55_be.dto.LoginDto;

public interface AuthenticationService {
    String login(LoginDto loginDto);
}
