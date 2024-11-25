package com.example.shop55_be.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String customerCode;
    @NotEmpty(message = "Không được bỏ trống")
    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Họ và tên không hợp lệ ")
    private String fullName;
    @NotEmpty(message = "Không được bỏ trống")
    @Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", message = "Email không hợp lệ")
    private String email;
    @NotEmpty(message = "Không được bỏ trống")
    @Size(min = 10,max = 10,message = "Số điện thoại có 10 số")
    @Pattern(regexp = "[0-9]*",message = "Số điện thoại không hợp lệ ")
    private String phoneNumber;
    private String passcode;
    @Temporal(TemporalType.DATE)
    private Date create_date;
    private String avata;
    private int customerStatus;

}
