package com.example.shop55_be.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String employeeCode;
    @NotEmpty(message = "Không được bỏ trống")
    @Pattern(regexp = "[a-zA-Zà-ỵ ]+",message = "Tên không có số hoặc kí tự đặc biệt")
    private String fullName;
    @NotEmpty(message = "Không được bỏ trống")
    @Pattern(regexp = "^[a-zA-Z]+[0-9]*@{1}[a-z]+\\.[a-z]+(\\.[a-z]+)*"
            ,message = "Không đúng định dạng mail")
    @Column(unique = true)
    private String email;
    @NotEmpty(message = "Không được bỏ trống")
    @Size(min = 10,max = 10,message = "Số điện thoại có 10 số")
    @Pattern(regexp = "[0-9]*",message = "Số điện thoại không có kí tự đặc biệt")
    @Column(unique = true)
    private String phoneNumber;
    private String passcode;
    @NotEmpty(message = "Không được bỏ trống")
    private String employeeAddress;
    @NotEmpty(message = "Không được bỏ trống")
    private String commune;
    @NotEmpty(message = "Không được bỏ trống")
    private String district;
    @NotEmpty(message = "Không được bỏ trống")
    private String city;
    private String nation;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    @ManyToOne()
    @JoinColumn(name = "role_id")
    private Role role;
    private String avata;
    private int employeeStatus;
}
