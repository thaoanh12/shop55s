package com.example.shop55_be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailDto {
    private String to;
    private String subject;
    private String content;
    private Map<String,Object> propos;
}

