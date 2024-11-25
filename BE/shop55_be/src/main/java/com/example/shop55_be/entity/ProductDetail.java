package com.example.shop55_be.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "product_detail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id ;

    @Column(name = "product_detail_code")
    private String productDetailCode ;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;

    @NotNull(message = "Không được bỏ trống")
    @Column(name = "price")
    private Double price ;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Colors color;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;

    @Column(name = "bar_code")
    private String barCode ;

    @NotNull(message = "Không được bỏ trống")
    @Column(name = "quantity")
    private int quantity ;

    @Column(name = "thumbnail")
    private String thumbnail ;

    @Column(name = "product_detail_status")
    private int productDetailStatus ;


}
