package com.example.shop55_be.service.impl;

import com.example.shop55_be.service.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
@Service
public class StorageServiceImpl implements StorageService {
    private final Path rootLocation;

    public StorageServiceImpl() {
        this.rootLocation = Paths.get("src/main/resources/static/qrcodes");
    }

    @Override
    public void store(MultipartFile file) {
        try {
            Path destinationFile = this.rootLocation.resolve(
                    Paths.get(file.getOriginalFilename())).normalize().toAbsolutePath();
            try(InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream,destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
        try {
                Files.createDirectories(rootLocation);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
