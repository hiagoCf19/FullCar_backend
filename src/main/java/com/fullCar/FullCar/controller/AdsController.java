package com.fullCar.FullCar.controller;

import com.fullCar.FullCar.dto.AdIdDTO;
import com.fullCar.FullCar.dto.AdRequestDTO;
import com.fullCar.FullCar.dto.AdsResponseListDTO;
import com.fullCar.FullCar.service.AdsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class AdsController {

    private  final AdsService adsService;
    @PostMapping("/create")
    @Transactional
    public ResponseEntity<AdIdDTO> registerAd(@RequestBody AdRequestDTO data, UriComponentsBuilder uriComponentsBuilder){
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
    public ResponseEntity<AdRequestDTO> getAd(@PathVariable Long id){
        var ad= adsService.getAd(id);
        return ResponseEntity.ok().body(ad);
    }
}

