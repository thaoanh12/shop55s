package com.example.shop55_be.service;

import com.example.shop55_be.dto.CustomerDto;
import com.example.shop55_be.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CustomerService {
    CustomerDto create(CustomerDto customerDto) throws Exception;
    Page<CustomerDto> pagination(Pageable pageable);
    List<Customer> getAll();
    Customer customerCreate(Customer customer) throws Exception;
    Customer sustomerUpdate(Customer customer);
    Boolean deletes(Long id);
   Customer details(Long id);
   Page<Customer> getAlls(Pageable pageable);
    Page<Customer> findCustomerByKeyWords( Pageable pageable,String keyword);
    Page<Customer> findCustomerBystatus( Pageable pageable,Long status);

    Customer findBycode(String code);
}
