package com.example.shop55_be.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;

@Service
public class AppService {
    public String generateQRCodeImage(String text)
            throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix matrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
        String folder = "E:\\DU_AN_TOT_NGHIEP\\DATN_2\\web_ban_quan_ao_nam_shop55\\BE\\shop55_be\\src\\main\\resources\\static\\qrcodes";
        String fileName = UUID.randomUUID()+text+".png";
        Path path = FileSystems.getDefault().getPath(folder +"\\"+ fileName);
        MatrixToImageWriter.writeToPath(matrix, "PNG", path);
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dbxcrlj9c",
                "api_key", "875675249534937",
                "api_secret", "UOYZPkpNeGJdtR7L7DW7dyqKZxw"));
        File imageFile = new File(folder+"/"+fileName);
        Map uploadResult = cloudinary.uploader().upload(imageFile, ObjectUtils.emptyMap());
        return (String) uploadResult.get("url");
    }
    public String upImageInClound(HttpServletRequest request, MultipartFile file)throws Exception{
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dbxcrlj9c",
                "api_key", "875675249534937",
                "api_secret", "UOYZPkpNeGJdtR7L7DW7dyqKZxw"));
        File imageFile = new File(file.getOriginalFilename());
        Map uploadResult = cloudinary.uploader().   upload(file.getBytes(), ObjectUtils.emptyMap());
        return (String) uploadResult.get("url");
    }
}
