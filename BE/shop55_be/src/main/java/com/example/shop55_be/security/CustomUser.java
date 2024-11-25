package com.example.shop55_be.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;

import javax.swing.*;
import java.util.Collection;
@Setter
@Getter
@ToString
@AllArgsConstructor@NoArgsConstructor
@Builder
public class CustomUser implements UserDetails {
    private String code;
    private String phoneNumber;
    private String email;
    private String password;
    private Collection<GrantedAuthority> listRole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.listRole;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
