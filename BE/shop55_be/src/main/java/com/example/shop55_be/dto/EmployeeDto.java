package com.example.shop55_be.dto;

import com.example.shop55_be.entity.Employee;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private long id;
    private String employeeCode;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String passcode;
    private String employeeAddress;
    private String commune;
    private String district;
    private String city;
    private String nation;
    private String crateDate;
    private String updateDate;
    private String avata;
    private int employeeStatus;
    private int roleId;
    private String roleName;

    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.employeeCode = employee.getEmployeeCode();
        this.fullName = employee.getFullName();
        this.email = employee.getEmail();
        this.phoneNumber = employee.getPhoneNumber();
        this.employeeAddress = employee.getEmployeeAddress();
        this.commune = employee.getCommune();
        this.district = employee.getDistrict();
        this.city = employee.getCity();
        this.nation = employee.getNation();
        this.crateDate = employee.getCreateDate().toString();
        this.avata = employee.getAvata();
        this.employeeStatus = employee.getEmployeeStatus();
        this.roleName = employee.getRole().getRoleName().name();
    }

}
