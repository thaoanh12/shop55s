package com.example.shop55_be.dto;

import com.example.shop55_be.entity.Colors;
import com.example.shop55_be.entity.ProductDetail;
import com.example.shop55_be.entity.Products;
import com.example.shop55_be.entity.Size;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDto {
    private Long id ;
    private String productDetailCode ;
    private Long productsId;
    private String productsName;
    private Double price ;
    private Long colorId;
    private String colorName;
    private Long sizeId;
    private String sizeName;
    private String barCode ;
    private int quantity ;
    private String thumbnail ;
    private int productDetailStatus ;

    public ProductDetailDto(ProductDetail productDetail) {
        this.id = productDetail.getId();
        this.productDetailCode = productDetail.getProductDetailCode();
        this.productsId = productDetail.getProducts().getId();
        this.productsName = productDetail.getProducts().getProductName();
        this.colorId = productDetail.getColor().getId();
        this.price = productDetail.getPrice();
        this.colorName = productDetail.getColor().getColorName();
        this.sizeId = productDetail.getSize().getId();
        this.sizeName = productDetail.getSize().getSizeName();
        this.barCode = productDetail.getBarCode();
        this.quantity = productDetail.getQuantity();
        this.thumbnail = productDetail.getThumbnail();
        this.productDetailStatus = productDetail.getProductDetailStatus();
    }
}
