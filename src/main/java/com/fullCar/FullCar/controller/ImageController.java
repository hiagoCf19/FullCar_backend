package com.fullCar.FullCar.controller;
import com.fullCar.FullCar.dto.ImageResponseDTO;
import com.fullCar.FullCar.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    @PostMapping("/upload-multiple/{ad_id}")
    public ResponseEntity<List<ImageResponseDTO>> uploadFiles(@RequestParam("files") List<MultipartFile> files, @PathVariable Long ad_id) {
        List<ImageResponseDTO> responses = imageService.uploadFiles(files, ad_id);
        return ResponseEntity.ok(responses);
    }
}
