package com.example.shop55_be.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SizeDto {
    private long id;
    private String size_code;
    private String size_name;
    private Date create_date;
    private int sizes_status;
}
