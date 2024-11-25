package com.example.shop55_be.service;

import com.example.shop55_be.dto.ColorDto;
import com.example.shop55_be.dto.SizeDto;
import com.example.shop55_be.entity.Colors;
import com.example.shop55_be.entity.Size;

import java.util.List;

public interface SizeService {
    List<Size> getAll();
    Size addSize(SizeDto sizeDto);
    Size deleteSize(Long id);
    Size DetailSize(Long id);
    Size updateSize(Long id ,SizeDto sizeDto);
}
