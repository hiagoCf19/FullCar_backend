package com.fullCar.FullCar.controller;
import com.fullCar.FullCar.dto.ImageResponseDTO;
import com.fullCar.FullCar.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/integration/aws-s3")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ImageResponseDTO> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam Long ad_id) {
        var eTag = imageService.uploadFile(file, ad_id);
        return ResponseEntity.ok(eTag);
    }
}