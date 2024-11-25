package com.example.shop55_be.service.impl;

import com.example.shop55_be.dto.LoginDto;
import com.example.shop55_be.jwt.JwtTokenProvider;
import com.example.shop55_be.security.CustomUser;
import com.example.shop55_be.service.AuthenticationService;
import com.example.shop55_be.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        return jwtTokenProvider.generateToken(customUser);
    }
}
