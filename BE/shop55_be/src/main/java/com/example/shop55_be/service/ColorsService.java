package com.example.shop55_be.service;

import com.example.shop55_be.dto.ColorDto;
import com.example.shop55_be.entity.Colors;
import com.example.shop55_be.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ColorsService {
    Page<Colors> findCustomerByKeyWords( Pageable pageable,String keyword);
    Page<Colors> findColor(Pageable pageable);
    List<Colors> getAll();
    Colors deleteColor(Long id);
    Colors DetailColor(Long id);
    Colors findById(long id);
    void saveColor(Colors colors);
    Page<Colors> findColorBystatus( Pageable pageable,Long status);
}
