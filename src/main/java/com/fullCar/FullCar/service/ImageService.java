package com.fullCar.FullCar.service;

import com.fullCar.FullCar.dto.ImageResponseDTO;
import com.fullCar.FullCar.model.Ads;
import com.fullCar.FullCar.model.Image;
import com.fullCar.FullCar.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ImageService {

    private static final Logger logger = LoggerFactory.getLogger(ImageService.class);
    private final S3Service s3Service;

    @Autowired
    private  ImageRepository imageRepository;
    @Autowired
    @Lazy
    private AdsService adsService;
    @Autowired
    public ImageService(S3Service s3Service){
        this.s3Service= s3Service;
    }

    public ImageResponseDTO uploadFile(MultipartFile file, Long ad_id){

        try{

            String uniqueFileName = generateUniqueFileName(Objects.requireNonNull(file.getOriginalFilename()));
           String imageUrl=  s3Service.sendToBucket(uniqueFileName, file);
           Ads ad= adsService.getAdById(ad_id);
           Image image= new Image();
           image.setAd(ad);
           image.setUrl(imageUrl);
           imageRepository.save(image);
           return new ImageResponseDTO(image.getId(), imageUrl, image.getAd().getId());

        } catch (Exception e){
            logger.error("Error uploading file: {}", file.getOriginalFilename(), e);
            throw new RuntimeException("Error uploading file: " + file.getOriginalFilename(), e);
        }
    }

    public List<ImageResponseDTO> uploadFiles(List<MultipartFile> files, Long ad_id){
        List<ImageResponseDTO> responses = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                String uniqueFileName = generateUniqueFileName(Objects.requireNonNull(file.getOriginalFilename()));
                String imageUrl = s3Service.sendToBucket(uniqueFileName, file);
                Ads ad = adsService.getAdById(ad_id);
                Image image = new Image();
                image.setAd(ad);
                image.setUrl(imageUrl);
                imageRepository.save(image);
                responses.add(new ImageResponseDTO(image.getId(), imageUrl, image.getAd().getId()));
            } catch (Exception e) {
                logger.error("Error uploading file: {}", file.getOriginalFilename(), e);
                throw new RuntimeException("Error uploading file: " + file.getOriginalFilename(), e);
            }
        }
        return responses;
    }

    public List<Image> getAllAdImagesById(Long ad_id){
        return imageRepository.findByAdId(ad_id);
    }

    private String generateUniqueFileName(String originalFilename) {
        // Gerar UUID
        String uuid = UUID.randomUUID().toString();

        // Obter a extensão do arquivo
        String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));

        // Gerar nome único com UUID e extensão
        return uuid + extension;
    }
}
