package com.example.shop55_be.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "colors")
public class  Colors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "color_code")
    private String colorCode;

    @NotEmpty( message = "Tên không để trống ")
    @Pattern(regexp = "[a-zA-Zà-ỵ ]+",message = "Tên không có số hoặc kí tự đặc biệt")
    @Column(name = "color_name")
    private String colorName;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "color_status")
    private Integer colorStatus;
}

