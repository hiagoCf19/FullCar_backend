package com.fullCar.FullCar.controller;

import com.fullCar.FullCar.dto.*;
import com.fullCar.FullCar.model.Ads;
import com.fullCar.FullCar.service.AdsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class AdsController {

    private  final AdsService adsService;
    @PostMapping("/create")
    @Transactional
    public ResponseEntity<AdIdDTO> registerAd(@RequestBody @Valid AdRequestDTO data, UriComponentsBuilder uriComponentsBuilder){
        AdIdDTO ad= adsService.createAd(data);
        var uri= uriComponentsBuilder.path("/ads/{id}").buildAndExpand(ad.id()).toUri();
        return ResponseEntity.created(uri).body(ad);
    }
    @GetMapping
    public ResponseEntity<AdsResponseListDTO> getAllAds(){
       var ads= adsService.getAllAds();
       return ResponseEntity.ok().body(ads);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AdResponseDTO> getAd(@PathVariable Long id){
        var ad= adsService.getAd(id);
        return ResponseEntity.ok().body(ad);
    }
    @GetMapping("user/{userId}")
    public ResponseEntity<List<AdByUserIdResponse>> getAdByUser(@PathVariable Long userId){
        var ads= adsService.getAllAdsByUser(userId);
        return ResponseEntity.ok().body(ads);
    }


}

