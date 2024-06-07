package com.fullCar.FullCar.service;

import com.fullCar.FullCar.dto.AdIdDTO;
import com.fullCar.FullCar.dto.AdRequestDTO;
import com.fullCar.FullCar.dto.AdsResponseListDTO;
import com.fullCar.FullCar.exception.AdsNotFound;
import com.fullCar.FullCar.model.Ads;
import com.fullCar.FullCar.repository.AdsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdsService {
    private final AdsRepository adsRepository;
    private final AccountService accountService;

    public AdIdDTO createAd(AdRequestDTO data){
       var ad= new Ads();
       ad.setTitle(data.title());
       ad.setDescription(data.description());
       ad.setUser_id(accountService.verifyAccountExist(data.user_id()));
       ad.setUser_price(data.user_price());
       ad.setBrand(data.brand());
       ad.setCode_fipe(data.code_fipe());
       ad.setFuel(data.fuel());
       ad.setModel(data.model());
       ad.setModel_year(data.model_year());
       ad.setFipe_price(data.fipe_price());
       ad.setReference_month(data.reference_month());
       adsRepository.save(ad);
       return new AdIdDTO(ad.getId());
    }

    public AdRequestDTO getAd(Long id){
        var ad= adsRepository.findById(id).orElseThrow(()-> new AdsNotFound("O anúncio que você procura não existe!"));
        return new AdRequestDTO(ad);
    }
    public AdsResponseListDTO getAllAds(){
        List<Ads> allAds= adsRepository.findAll();
        var list= allAds.stream().map(AdRequestDTO::new).toList();
        return new AdsResponseListDTO(list);
    }
}
