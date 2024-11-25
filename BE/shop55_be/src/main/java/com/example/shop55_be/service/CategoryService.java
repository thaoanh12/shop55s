package com.example.shop55_be.service;

import com.example.shop55_be.entity.Categories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<Categories> getAll();
    void add(Categories categories);
    boolean delete(Long id);
    Categories detail(Long id);
    Categories findById(long id);
    Page<Categories> findCategories(Pageable pageable);
    Page<Categories> findCategoriesByKeyWords(Pageable pageable, String keyword);
    Page<Categories> findCategorBystatus( Pageable pageable,Long status);
    List<Categories> categorisPants();
    List<Categories> categorisShirt();

}
