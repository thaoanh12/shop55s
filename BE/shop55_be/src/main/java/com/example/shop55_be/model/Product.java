package com.example.shop55_be.model;

import com.example.shop55_be.entity.Categories;
import com.example.shop55_be.entity.Fabrics;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id ;

    @Column(name = "product_code")
    private String productCode ;

    @NotEmpty(message = "Không được bỏ trống")
    @Column(name = "product_name")
    private String productName ;

    @NotEmpty(message = "Không được bỏ trống")
    @Column(name = "made_in")
    private String madeIn ;

    @NotEmpty(message = "Không được bỏ trống")
    @Column(name = "product_describe")
    private String productDescribe ;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories categories;

    @ManyToOne
    @JoinColumn(name = "fabric_id")
    private Fabrics fabric;

    @NotEmpty(message = "Không được bỏ trống")
    @Column(name = "thumbnail")
    private String thumbnail ;

    @NotEmpty(message = "Không được bỏ trống")
    @Column(name = "weight")
    private String weight ;

    @Column(name = "create_date")
    private Date createDate ;

    @Column(name = "product_status")
    private int productStatus ;

    @Column(name = "min_price")
    private Double minPrice;
}

