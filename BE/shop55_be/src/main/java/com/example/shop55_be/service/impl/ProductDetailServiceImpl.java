package com.example.shop55_be.service.impl;

import com.example.shop55_be.entity.*;
import com.example.shop55_be.reposistory.ColorsRepo;
import com.example.shop55_be.reposistory.ProductDetailRepo;
import com.example.shop55_be.reposistory.ProductRepo;
import com.example.shop55_be.reposistory.SizeRepo;
import com.example.shop55_be.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    private ProductDetailRepo productDetailRepo;
    @Autowired
    private ColorsRepo colorsRepo;
    @Autowired
    private SizeRepo sizeRepo;
    @Autowired
    private ProductRepo productRepo;

@Autowired
private AppService appService;



    public List<ProductDetail> getAll() {
        return productDetailRepo.findAll();
    }

    @Override
    public ProductDetail update(ProductDetail productDetail) {
        return productDetailRepo.save(productDetail);
    }

    @Override
    public List<ProductDetail> save(ProductDetail productDetail, Long colorId, Long [] sizeIds, Long productId)throws Exception {
        List<ProductDetail> productDetails = new ArrayList<>();
        for(long sizeId : sizeIds){
            ProductDetail newProductDetail = new ProductDetail();
            Colors colors = colorsRepo.findById(colorId).get();
            Size size = sizeRepo.findById(sizeId).get();
            newProductDetail.setColor(colors);
            newProductDetail.setSize(size);
            newProductDetail.setThumbnail(productDetail.getThumbnail());
            newProductDetail.setPrice(productDetail.getPrice());
            newProductDetail.setQuantity(productDetail.getQuantity());
            Products product = productRepo.findById(productId).get();
            newProductDetail.setProducts(product);
            if(productDetail.getId()==null){
                String productDetailCode = "SP"+new Random().nextInt(1000)+"-"+ newProductDetail.getColor().getId()+"-"
                        +newProductDetail.getSize().getId();
                newProductDetail.setProductDetailCode(productDetailCode);
                String urlQrcode = appService.generateQRCodeImage(productDetailCode);
                newProductDetail.setBarCode(urlQrcode);
            }
            productDetails.add(newProductDetail);
        }
        return productDetailRepo.saveAll(productDetails);
    }

    @Override
    public ProductDetail findById(Long id) {
        ProductDetail productDetail = productDetailRepo.findById(id).get();
        return productDetail;
    }

    @Override
    public ProductDetail findByCode(String code) {
        return productDetailRepo.findByProductDetailCode(code);
    }


    @Override
    public List<ProductDetail> findProductDetailByProductId(Long id) {
        return productDetailRepo.findProductDetailByProductId(id);
    }

    @Override
    public Page<ProductDetail> sortAndFilter(Pageable pageable, Long colorID, Long sizeId, Long id, String productName,String productDetailCode) {
        return productDetailRepo.sortAndFilter(pageable,colorID,sizeId,id,productName,productDetailCode);
    }

    @Override
    public ProductDetail findProductDetailBySizeAndAndColor(Long productId,Long colorID, Long sizeId) {
        return productDetailRepo.findProductDetailBySizeAndAndColor(productId,colorID,sizeId);
    }

    @Override
    public Page<ProductDetail> getBestSellingProducts(Pageable pageable) {
        return productDetailRepo.getBestSellingProductDetails(pageable);
    }

    @Override
    public Page<ProductDetail> getProductNew(Pageable pageable) {
        return productDetailRepo.getProductNew(pageable);
    }


}
