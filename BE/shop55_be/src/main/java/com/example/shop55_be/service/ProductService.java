package com.example.shop55_be.service;

import com.example.shop55_be.entity.OrderDetail;
import com.example.shop55_be.entity.ProductDetail;
import com.example.shop55_be.entity.Products;
import com.example.shop55_be.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<Products> getAll();
    Page<Products> page(Pageable pageable);
    Page<Product> homeProducAll(Pageable pageable);
    Products update(Products product);
    Products save(Products product, Long categoryId, Long fabicId);
    Products findById(Long id);
    Page<Products> getNonDiscountedProduct(Pageable pageable);
    Page<Products> searchProduct(Pageable pageable,String productName,String productDetailCode);
    List<Product> bestSellingProduct();
    Page<Product> productMensShirt(Pageable pageable , String productName);
    List<Products> productShirt ();
    List<Products> productPants ();
    Page<Product> getProductsHomeFilterAscending(Pageable pageable);
    Page<Product> getProductsHomeFilterDecrease(Pageable pageable);
    List<Products> getTopSellingProducts();
    Page<Products> getProduct(Pageable pageable);
    Page<Product> getTu(Pageable pageable);
    Page<Product> getProductsOrderByCreateDateDesc(Pageable pageable);
    Page<Product> getProductsHomeFilterAscendingShirt(Pageable pageable);
    Page<Product> getProductsHomeFilterDecreaseShirt(Pageable pageable);

}
