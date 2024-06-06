package com.fullCar.FullCar.controller;

import com.fullCar.FullCar.dto.AdsIdDTO;
import com.fullCar.FullCar.dto.AdsRequestDTO;
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
    public ResponseEntity<AdsIdDTO> registerAds(@RequestBody AdsRequestDTO data, UriComponentsBuilder uriComponentsBuilder){
        AdsIdDTO ads= adsService.createAds(data);
        var uri= uriComponentsBuilder.path("/ads/{id}").buildAndExpand(ads.id()).toUri();
        return ResponseEntity.created(uri).body(ads);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AdsRequestDTO> getAds(@PathVariable Long id){
        var ads= adsService.getAds(id);
        return ResponseEntity.ok().body(ads);
    }
}

