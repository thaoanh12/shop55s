package com.example.shop55_be.security;

import com.example.shop55_be.model.ERole;
import com.example.shop55_be.model.User;
import com.example.shop55_be.reposistory.EmployeeRepo;
import com.example.shop55_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userService.findByEmail(username);
            List<GrantedAuthority> roles = new ArrayList<>();
            switch (    user.getType()){
                case "employee" -> {
                    roles.add(new SimpleGrantedAuthority(
                            employeeRepo.
                                    findByEmployeeCode(user.getCode())
                                    .getRole().getRoleName().name()
                    ));
                }
                case "customer" ->{
                    roles.add(new SimpleGrantedAuthority(ERole.ROLE_CUSTOMER.name()));
                }
            }

            return CustomUser.builder()
                    .code(user.getCode())
                    .phoneNumber(user.getPhoneNumber())
                    .email(user.getEmail())
                    .password(user.getPasscode())
                    .listRole(roles)
                    .build();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
