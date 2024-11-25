package com.example.shop55_be.service.impl;

import com.example.shop55_be.entity.Categories;
import com.example.shop55_be.entity.Fabrics;
import com.example.shop55_be.entity.Products;
import com.example.shop55_be.model.Product;
import com.example.shop55_be.reposistory.CategoryRepo;
import com.example.shop55_be.reposistory.FabricsRepo;
import com.example.shop55_be.reposistory.ProductDetailRepo;
import com.example.shop55_be.reposistory.ProductHomeRepo;
import com.example.shop55_be.reposistory.ProductRepo;
import com.example.shop55_be.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private FabricsRepo fabricsRepo;
    @Autowired
    private ProductDetailRepo productDetailRepo;
    @Autowired
    private ProductHomeRepo productHomeRepo;

    public List<Products> getAll() {
        return productRepo.findAll();
    }

    @Override
    public Page<Products> page(Pageable pageable) {
        return productRepo.page(pageable);
    }

    @Override
    public Page<Product> homeProducAll(Pageable pageable) {
        return productHomeRepo.homeProducAll(pageable);
    }

    @Override
    public Products update(Products product) {
        product.getThumbnail();
        return productRepo.save(product);
    }

    @Override
    public Products save(Products product, Long categoryId, Long fabicId) {
        Categories categorie = categoryRepo.findById(categoryId).get();
        Fabrics fabric = fabricsRepo.findById(fabicId).get();
        product.setCategories(categorie);
        product.setFabric(fabric);
        String productCode = "sp" + new Random().nextInt(10000000);
        product.setProductCode(productCode);
        product.setCreateDate(new Date());
        return productRepo.save(product);
    }

    @Override
    public Products findById(Long id) {
        Products products = productRepo.findById(id).get();
        return products;
    }

    @Override
    public Page<Products> getNonDiscountedProduct(Pageable pageable) {
        return productRepo.getNonDiscountedProducts(pageable);
    }

    @Override
    public Page<Products> searchProduct(Pageable pageable, String productName, String productCode) {
        return productRepo.searchProduct(pageable, productName, productCode);
    }

    @Override
    public List<Product> bestSellingProduct() {
        return productHomeRepo.bestSellingProduct();
    }

    @Override
    public Page<Product> productMensShirt(Pageable pageable, String productName) {
        return productHomeRepo.productMensShirt(pageable, productName);
    }

    @Override
    public List<Products> productShirt() {
        return productRepo.productShirt();
    }

    @Override
    public List<Products> productPants() {
        return productRepo.ProductPants();
    }

    @Override
    public Page<Product> getProductsHomeFilterAscending(Pageable pageable) {
        return productHomeRepo.getProductsHomeFilterAscending(pageable);
    }

    @Override
    public Page<Product> getProductsHomeFilterDecrease(Pageable pageable) {
        return productHomeRepo.getProductsHomeFilterDecrease(pageable);
    }

    @Override
    public List<Products> getTopSellingProducts() {
        List<Products> products = productRepo.getAllProducts();
        Map<Products, Integer> productsIntegerMap = new HashMap<>();
        for (Products products1 : products
        ) {
//            int sales =

        }
        return null;
    }

    @Override
    public Page<Products> getProduct(Pageable pageable) {
        return productRepo.getProduct(pageable);
    }

    @Override
    public Page<Product> getTu(Pageable pageable) {
        return productHomeRepo.getTu(pageable);
    }

    @Override
    public Page<Product> getProductsOrderByCreateDateDesc(Pageable pageable) {
        return productHomeRepo.getProductsOrderByCreateDateDesc(pageable);
    }

    @Override
    public Page<Product> getProductsHomeFilterAscendingShirt(Pageable pageable) {
        return productHomeRepo.getProductsHomeFilterAscendingShirt(pageable);
    }

    @Override
    public Page<Product> getProductsHomeFilterDecreaseShirt(Pageable pageable) {
        return productHomeRepo.getProductsHomeFilterDecreaseShirt(pageable);
    }


}
