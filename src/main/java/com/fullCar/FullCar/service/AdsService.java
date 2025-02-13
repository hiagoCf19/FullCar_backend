package com.fullCar.FullCar.service;

import com.fullCar.FullCar.dto.*;
import com.fullCar.FullCar.exception.AdsNotFound;
import com.fullCar.FullCar.model.Account;
import com.fullCar.FullCar.model.Ads;
import com.fullCar.FullCar.model.Image;
import com.fullCar.FullCar.repository.AdsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdsService {
    private final AdsRepository adsRepository;
    private final AccountService accountService;

    @Autowired
    @Lazy
    private ImageService imageService;

    public AdIdDTO createAd(AdRequestDTO data){
        var ad= new Ads();
        ad.setTitle(data.title());
        ad.setDescription(data.description());
        ad.setUser_id(accountService.getAccountById(data.user_id()));
        ad.setUser_price(data.user_price());
        ad.setBrand(data.brand());
        ad.setCode_fipe(data.code_fipe());
        ad.setFuel(data.fuel());
        ad.setModel(data.model());
        ad.setModel_year(data.model_year());
        ad.setFipe_price(data.fipe_price());
        ad.setReference_month(data.reference_month());
        ad.setCreated_at(LocalDateTime.now());
        ad.setKilometers_driven(data.kilometers_driven());
        ad.setType_of_vehicle(data.type_of_vehicle());
        ad.setTraffic_signs(data.traffic_signs());
        ad.setCar_color(data.car_color());
        ad.setType_of_direction(data.type_of_direction());
        ad.setGear_box(data.gear_box());
        ad.setEngine_power(data.engine_power());
        adsRepository.save(ad);
        return new AdIdDTO(ad.getId());
    }

    public AdResponseDTO getAd(Long id){
        var ad = adsRepository.findByIdWithUser(id)
                .orElseThrow(() -> new AdsNotFound("The ad you are looking for was not found"));

        // Pega o usuário associado ao anúncio
        Account user = ad.getUser_id();

        // Passa o anúncio, imagens e usuário ao DTO
        return new AdResponseDTO(ad, getAllAdImages(id), user);
    }

    public AdsResponseListDTO getAllAds() {
        List<Ads> allAds = adsRepository.findAll();
        List<AdResponseDTO> adResponseDTOs = allAds
                .stream()
                .map(ad -> new AdResponseDTO(ad, getAllAdImages(ad.getId()), ad.getUser_id()))
                .collect(Collectors.toList());
        return new AdsResponseListDTO(adResponseDTOs);
    }
    public Ads getAdById(Long id){
        return adsRepository.findById(id).orElseThrow(()-> new AdsNotFound("The ad you are looking for was not found"));
    }
    public List<AdByUserIdResponse> getAllAdsByUser(Long userId){
        var ads= adsRepository.getAdsByUser(userId);
        return ads.stream()
                .map(ad -> {
                    var images= getAllAdImages(ad.getId());
                    return new AdByUserIdResponse(ad, images);
                })
                .toList();
    }

    private List<ImageResponseDTO> getAllAdImages(Long ad_id){
        List<Image> images= imageService.getAllAdImagesById(ad_id);
        return images.stream()
                .map(image -> new ImageResponseDTO(image.getId(), image.getUrl(), image.getAd().getId()))
                .collect(Collectors.toList());
    }
}