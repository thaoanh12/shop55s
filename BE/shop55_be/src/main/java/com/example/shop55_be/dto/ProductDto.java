package com.example.shop55_be.dto;

import com.example.shop55_be.entity.Products;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id ;
    private String productCode ;
    private String productName ;
    private String madeIn ;
    private String productDescribe ;
    private Long categoriesId;
    private String categoriesName;
    private Long fabricId;
    private String fabricName;
    private String thumbnail ;
    private String weight ;
    private Date createDate ;
    private int productStatus ;
    private long quantity ;

    public ProductDto(Products products,long quantity) {
        this.id = products.getId();
        this.productCode = products.getProductCode();
        this.productName = products.getProductName();
        this.madeIn = products.getMadeIn();
        this.productDescribe = products.getProductDescribe();
        this.categoriesId = products.getCategories().getId();
        this.categoriesName = products.getCategories().getCategoryName();
        this.fabricId = products.getFabric().getId();
        this.fabricName = products.getFabric().getFabricName();
        this.thumbnail = products.getThumbnail();
        this.weight = products.getWeight();
        this.createDate = products.getCreateDate();
        this.productStatus = products.getProductStatus();
        this.quantity = quantity;
    }
}
