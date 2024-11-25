package com.example.shop55_be.service.impl;

import com.example.shop55_be.dto.FabricsDto;
import com.example.shop55_be.entity.Fabrics;
import com.example.shop55_be.reposistory.FabricsRepo;
import com.example.shop55_be.service.FabricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class FabricsServiceImpl implements FabricsService {
    @Autowired
    private FabricsRepo fabricsRepo ;
    @Override
    public List<Fabrics> getAll() {
        return fabricsRepo.findAll();
    }

    @Override
    public void add(Fabrics fabrics) {
        fabricsRepo.save(fabrics);
    }

    @Override
    public Fabrics detail(Long id) {
        return fabricsRepo.findById(id).get();
    }

    @Override
    public Fabrics finbyid(Long id) {
        return fabricsRepo.findById(id).get();
    }

    @Override
    public Page<Fabrics> findFabrics(Pageable pageable) {
        return fabricsRepo.findFabricsBy(pageable);
    }

    @Override
    public Page<Fabrics> findFabricsByKeyWords(Pageable pageable, String keyword) {
        return fabricsRepo.findFabricsByKeyWord(pageable, keyword);
    }

    @Override
    public Fabrics update(FabricsDto fabricsDto) {
        try {
            Fabrics fabrics = Fabrics.builder()
                    .id(fabricsDto.getId())
                    .fabricName(fabricsDto.getFabricName())
                    .createDate(fabricsDto.getCreateDate())
                    .fabricStatus(fabricsDto.getFabricStatus())
                    .build();
            return fabricsRepo.save(fabrics);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            fabricsRepo.deleteById(id);
            return true ;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Page<Fabrics> findFabricsBystatus(Pageable pageable, Long status) {
        return fabricsRepo.findfabricBystatus(pageable,status);
    }
}
