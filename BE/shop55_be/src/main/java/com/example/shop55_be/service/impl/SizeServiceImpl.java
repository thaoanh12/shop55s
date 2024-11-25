package com.example.shop55_be.service.impl;

import com.example.shop55_be.dto.SizeDto;
import com.example.shop55_be.entity.Size;
import com.example.shop55_be.reposistory.SizeRepo;
import com.example.shop55_be.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    SizeRepo sizeRepo;

    @Override
    public List<Size> getAll() {
        return sizeRepo.findAll();
    }

    @Override
    public Size addSize(SizeDto sizeDto)  {
       try {
           Size size = Size.builder()
                   .sizeCode(sizeDto.getSize_code())
                   .sizeName(sizeDto.getSize_name())
                   .createDate(sizeDto.getCreate_date())
                   .sizesStatus(sizeDto.getSizes_status())
                   .build();
           return sizeRepo.save(size);
       }catch (Exception e){
           e.printStackTrace();
           return null;
       }
    }

    @Override
    public Size deleteSize(Long id) {
        Optional<Size> optional = sizeRepo.findById(id);

        return optional.map(size -> {
            sizeRepo.delete(size);
            return size;
        }).orElse(null);
    }

    @Override
    public Size DetailSize(Long id) {
        return null;
    }

    @Override
    public Size updateSize(Long id, SizeDto sizeDto) {
        return sizeRepo.findById(id).map(size -> {
            size.setSizeCode(sizeDto.getSize_code());
            size.setSizeName(sizeDto.getSize_name());
            size.setCreateDate(new Date());
            size.setSizesStatus(sizeDto.getSizes_status());
            return sizeRepo.save(size);
        }).orElse(null);
    }
}
