package com.example.shop55_be.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
public class LoginDto {
    @NotBlank(message = "Không được bỏ trống")
    private String email;
    @NotBlank(message = "Không được bỏ trống")
    private String password;
}
