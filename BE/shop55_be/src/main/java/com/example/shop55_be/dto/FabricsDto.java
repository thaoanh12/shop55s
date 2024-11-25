package com.example.shop55_be.dto;

import com.example.shop55_be.entity.Fabrics;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FabricsDto {
    private Long id;
    private String fabricName;
    private Date createDate;
    private int fabricStatus;

    public  FabricsDto(Fabrics fabrics){
        this.id = fabrics.getId();
        this.fabricName = fabrics.getFabricName();
        this.createDate = fabrics.getCreateDate();
        this.fabricStatus = fabrics.getFabricStatus();

    }
}
