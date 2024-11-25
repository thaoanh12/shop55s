package com.example.shop55_be.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
@Entity
public class User {
    @Id
    private String code;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String passcode;
    private String avata;
    private int status;
    private String type;
}
