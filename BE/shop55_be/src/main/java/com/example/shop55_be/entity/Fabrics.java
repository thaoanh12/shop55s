package com.example.shop55_be.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "fabrics")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Fabrics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty( message = "Tên không để trống ")
    @Pattern(regexp = "[a-zA-Zà-ỵ ]+",message = "Tên không có số hoặc kí tự đặc biệt")
    @Column(name = "fabric_name")
    private String fabricName;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "fabric_status")
    private int fabricStatus;
}

