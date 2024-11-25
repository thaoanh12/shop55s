package com.example.shop55_be.service.impl;

import com.example.shop55_be.dto.AddressDto;
import com.example.shop55_be.entity.Address;
import com.example.shop55_be.reposistory.AddressRepo;
import com.example.shop55_be.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepo addressRepo;

    @Override
    public List<AddressDto> getAll() {
        return addressRepo.findAll().stream()
                .map(AddressDto::new).collect(Collectors.toList());
    }

    @Override
    public Address save(Address address) {
        return addressRepo.save(address);
    }
}
