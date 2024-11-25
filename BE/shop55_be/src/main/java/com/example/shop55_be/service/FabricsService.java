package com.example.shop55_be.service;

import com.example.shop55_be.dto.CategoryDto;
import com.example.shop55_be.dto.FabricsDto;
import com.example.shop55_be.entity.Categories;
import com.example.shop55_be.entity.Fabrics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface FabricsService {
    List<Fabrics> getAll();

    void add(Fabrics fabrics);

    Fabrics detail(Long id);

    Fabrics finbyid(Long id);

    Page<Fabrics> findFabrics(Pageable pageable);

    Page<Fabrics> findFabricsByKeyWords(Pageable pageable, String keyword);


    Fabrics update(FabricsDto fabricsDto);

    boolean delete(Long id);
    Page<Fabrics> findFabricsBystatus(Pageable pageable, Long status);
}
