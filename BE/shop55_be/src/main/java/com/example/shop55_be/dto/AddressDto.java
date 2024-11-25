package com.example.shop55_be.dto;

import com.example.shop55_be.entity.Address;
import com.example.shop55_be.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private String customerCode;
    private String customerAddress;
    private String commune;
    private String district;
    private String city;
    private String nation;

    public AddressDto(Address address) {
        this.customerCode = address.getCustomer().getCustomerCode();
        this.customerAddress = address.getCustomerAddress();
        this.commune = address.getCommune();
        this.district = address.getDistrict();
        this.city = address.getCity();
        this.nation = address.getNation();
    }
}
