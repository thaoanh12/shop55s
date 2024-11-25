package com.example.shop55_be.reposistory;

import com.example.shop55_be.entity.Categories;
import com.example.shop55_be.entity.Colors;
import com.example.shop55_be.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Categories, Long> {
    @Query(value = "SELECT c FROM Categories c")
    Page<Categories> findCategories(Pageable pageable);

    @Query("select categories from Categories categories " +
            "where categories.categoryName like %:categoryName% ")
    Page<Categories> findCategoriesByKeyWord(Pageable pageable,@Param("categoryName")String categoryName);

    @Query(value = "select * from categories where (category_status = :status or :status is null)", nativeQuery = true)
    Page<Categories> findCategorBystatus(Pageable pageable, @Param("status") Long status);
    @Query(value = "select * from categories where category_name like '%Ao%'",nativeQuery = true)
    List<Categories> categorisShirt();
    @Query(value = "select * from categories where category_name like '%Quan%'",nativeQuery = true)
    List<Categories> categorisPants();
}
