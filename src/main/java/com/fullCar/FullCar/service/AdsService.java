package com.fullCar.FullCar.service;

import com.fullCar.FullCar.dto.AdsIdDTO;
import com.fullCar.FullCar.dto.AdsRequestDTO;
import com.fullCar.FullCar.model.Ads;
import com.fullCar.FullCar.repository.AdsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdsService {
    private final AdsRepository adsRepository;
    private final AccountService accountService;

    public AdsIdDTO createAds(AdsRequestDTO data){
       var ads= new Ads();
       ads.setTitle(data.title());
       ads.setDescription(data.description());
       ads.setUser_id(accountService.getAccountById(data.user_id()));
       ads.setUser_price(data.user_price());
       ads.setBrand(data.brand());
       ads.setCode_fipe(data.code_fipe());
       ads.setFuel(data.fuel());
       ads.setModel(data.model());
       ads.setModel_year(data.model_year());
       ads.setFipe_price(data.fipe_price());
       ads.setReference_month(data.reference_month());
       adsRepository.save(ads);
       return new AdsIdDTO(ads.getId());
    }
    public AdsRequestDTO getAds (Long id){
        var ads= adsRepository.findById(id).orElseThrow(()-> new RuntimeException("Anúncio não encontrado"));
        return new AdsRequestDTO(ads);
    }
}
