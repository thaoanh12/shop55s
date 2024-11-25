package com.example.shop55_be.dto;

import com.example.shop55_be.entity.Customer;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private long id;
    private String customerCode;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String passcode;
    private String avata;
    private String customerAddress;
    private String commune;
    private String district;
    private String city;
    private String nation;
    private Date updateDate;
    private Date crateDate;

    public CustomerDto(Customer customer) {
        this.customerCode = customer.getCustomerCode();
        this.fullName = customer.getFullName();
        this.email = customer.getEmail();
        this.phoneNumber = customer.getPhoneNumber();
        passcode = customer.getPasscode();
        this.avata = customer.getAvata();
    }
}
