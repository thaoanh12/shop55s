package com.example.shop55_be.service.impl;

import com.example.shop55_be.entity.Customer;
import com.example.shop55_be.entity.Discount;
import com.example.shop55_be.reposistory.DiscountRepo;
import com.example.shop55_be.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Service
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    private DiscountRepo discountRepo;
    @Override
    public List<Discount> getAll() {
        return discountRepo.findAll();
    }



    @Override
    public Discount addDiscount(Discount discount){
        Date date = new Date();
        if(date.before(discount.getCreateDate())){
            discount.setDiscountStatus(0);
        }
        return discountRepo.save(discount);
    }

    @Override
    public Page<Discount> findDiscount(Pageable pageable) {
        return discountRepo.findDiscount(pageable);
    }

    @Override
    public Discount findByCode(String code) {
        return null;
    }



    @Override
    public Discount details(Long id) {
        return discountRepo.findById(id).get();
    }

    @Override
    public Discount dicountUpdate(Discount discount) {
        return discountRepo.save(discount);
    }

    @Override
    public Page<Discount> findCustomerBystatus(Pageable pageable, Boolean status) {
        return discountRepo.findDiscountByStatus(pageable,status);
    }

    @Override
    public Page<Discount> findCustomerByType(Pageable pageable, Boolean type) {
        return discountRepo.findDiscountByType(pageable,type);
    }

    @Override
    public Page<Discount> findDiscountByKeyWords(Pageable pageable, String keyword) {
        return discountRepo.findDiscountByKeyWord(
                pageable,
                "%" + keyword + "%",
                null,
                null
        );
    }

}

