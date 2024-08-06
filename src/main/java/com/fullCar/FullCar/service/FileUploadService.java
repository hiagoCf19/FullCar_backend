package com.fullCar.FullCar.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadService.class);

    private final S3Service s3Service;

    @Autowired
    public FileUploadService(S3Service s3Service){
        this.s3Service= s3Service;
    }
    public String uploadFile(MultipartFile file){

        try{
           String fileName= file.getOriginalFilename();
            return s3Service.uploadFile(fileName, file);
        } catch (Exception e){
            logger.error("Error uploading file: {}", file.getOriginalFilename(), e);
            throw new RuntimeException("Error uploading file: " + file.getOriginalFilename(), e);
        }
    }
}
