package com.example.shop55_be.dto;

import com.example.shop55_be.entity.Colors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColorDto {

    private long id;
    private String colorName;
    private String colorCode;
    private Date createDate;
    private Integer colorStatus;
    public ColorDto(Colors colors) {
        this.id = colors.getId();
        this.colorName = colors.getColorName();
        this.colorCode = colors.getColorCode();
        this.createDate = colors.getCreateDate();
    }
}
