package com.example.shop55_be.service;

import com.example.shop55_be.entity.Customer;
import com.example.shop55_be.entity.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface DiscountService {
    List<Discount> getAll();
    Discount addDiscount(Discount discount) throws Exception;
    Page<Discount> findDiscount(Pageable pageable);



    Discount findByCode(String code);

    Discount details(Long id);
    Discount dicountUpdate(Discount discount);

    Page<Discount> findCustomerBystatus( Pageable pageable,Boolean status);

    Page<Discount> findCustomerByType( Pageable pageable,Boolean type);

    Page<Discount> findDiscountByKeyWords(Pageable pageable, String keyword);




}
