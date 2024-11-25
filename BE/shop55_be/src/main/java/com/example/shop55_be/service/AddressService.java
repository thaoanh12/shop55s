package com.example.shop55_be.service;

import com.example.shop55_be.dto.AddressDto;
import com.example.shop55_be.dto.CustomerDto;
import com.example.shop55_be.entity.Address;

import java.util.List;

public interface AddressService {
    List<AddressDto> getAll();
    Address save(Address address);
}
