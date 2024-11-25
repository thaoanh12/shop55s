package com.example.shop55_be.dto;

import com.example.shop55_be.entity.Categories;
//import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id ;
    private String categoryName ;
    private Date createDate ;
    private int categoryStatus ;

    public CategoryDto(Categories category){
        this.id = category.getId();
        this.categoryName = category.getCategoryName();
        this.createDate = category.getCreateDate();
    }
}
