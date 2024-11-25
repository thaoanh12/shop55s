package com.example.shop55_be.service.impl;

import com.example.shop55_be.dto.ColorDto;
import com.example.shop55_be.entity.Colors;
import com.example.shop55_be.reposistory.ColorsRepo;
import com.example.shop55_be.service.ColorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ColorsServiceImpl implements ColorsService {

    @Autowired
    ColorsRepo colorsRepo;


    @Override
    public Page<Colors>  findCustomerByKeyWords( Pageable pageable, String keyword) {
        return colorsRepo.findCustomerByKeyWord( pageable,keyword,keyword);
    }

    @Override
    public Page<Colors> findColor(Pageable pageable) {
        return colorsRepo.findColor(pageable);
    }

    @Override
    public List<Colors> getAll() {
        return colorsRepo.findAll();
    }
    @Override
    public Colors deleteColor(Long id) {
        Optional<Colors> optional = colorsRepo.findById(id);
        return optional.map(colors -> {
            colorsRepo.delete(colors);
            return colors;
        }).orElse(null);
    }

    @Override
    public Colors DetailColor(Long id) {
        return colorsRepo.findById(id).get();
    }

    public Colors findById(long id) {
        return null;
    }

    @Override
    public void saveColor(Colors colors) {
         colorsRepo.save(colors);
    }

    @Override
    public Page<Colors> findColorBystatus(Pageable pageable, Long status) {
        return colorsRepo.findColorBystatus(pageable,status);
    }
}
