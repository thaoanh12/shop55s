package com.example.shop55_be.service;

import com.example.shop55_be.entity.OrderDetail;
import com.example.shop55_be.entity.ProductDetail;
import com.example.shop55_be.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductDetailService {
    List<ProductDetail> getAll();
    ProductDetail update(ProductDetail productDetail);
    List<ProductDetail> save(ProductDetail productDetail, Long colorId, Long [] sizeId,Long productId) throws Exception;
    ProductDetail findById(Long id);
    ProductDetail findByCode(String code);
    List<ProductDetail> findProductDetailByProductId(Long id);
    Page<ProductDetail> sortAndFilter(Pageable pageable,Long colorID,Long sizeId,Long id,String productName,String productDetailCode);
    ProductDetail findProductDetailBySizeAndAndColor(Long productId,Long colorID,Long sizeId);
    Page<ProductDetail> getBestSellingProducts(Pageable pageable);
    Page<ProductDetail> getProductNew(Pageable pageable);
}
