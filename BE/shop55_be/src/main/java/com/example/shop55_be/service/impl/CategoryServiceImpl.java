package com.example.shop55_be.service.impl;

import com.example.shop55_be.dto.CategoryDto;
import com.example.shop55_be.entity.Categories;
import com.example.shop55_be.entity.Colors;
import com.example.shop55_be.reposistory.CategoryRepo;
import com.example.shop55_be.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public List<Categories> getAll() {
        return categoryRepo.findAll();
    }

    @Override
    public void add(Categories categories) {
        categoryRepo.save(categories);
    }

    @Override
    public boolean delete(Long id) {
        try {
            categoryRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Categories detail(Long id) {
        return categoryRepo.findById(id).get();
    }

    @Override
    public Categories findById(long id) {
        return categoryRepo.findById(id).get();
    }

    @Override
    public Page<Categories> findCategories(Pageable pageable) {
        return categoryRepo.findCategories(pageable);
    }

    @Override
    public Page<Categories> findCategoriesByKeyWords(Pageable pageable, String keyword) {
        return categoryRepo.findCategoriesByKeyWord(pageable, keyword);

    }

    @Override
    public Page<Categories> findCategorBystatus(Pageable pageable, Long status) {
        return categoryRepo.findCategorBystatus(pageable, status);
    }

    @Override
    public List<Categories> categorisPants() {
        return categoryRepo.categorisPants();
    }

    @Override
    public List<Categories> categorisShirt() {
        return categoryRepo.categorisShirt();
    }

}
