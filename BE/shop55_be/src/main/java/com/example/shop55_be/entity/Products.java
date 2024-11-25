package com.example.shop55_be.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Products {
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

    @Column(name = "thumbnail")
    private String thumbnail ;

    @NotEmpty(message = "Không được bỏ trống")
    @Column(name = "weight")
    private String weight ;

    @Column(name = "create_date")
    private Date createDate ;

    @Column(name = "product_status")
    private int productStatus ;

}
