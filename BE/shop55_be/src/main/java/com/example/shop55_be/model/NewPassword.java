package com.example.shop55_be.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPassword {
    @NotEmpty(message = "Không được bỏ trống")
    private String oldPassword;
    @NotEmpty(message = "Không được bỏ trống")
    private String newPassword;
}
