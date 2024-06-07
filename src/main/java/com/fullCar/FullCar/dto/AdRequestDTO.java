package com.fullCar.FullCar.dto;

import com.fullCar.FullCar.model.Ads;

import java.time.LocalDateTime;


public record AdRequestDTO(
         Long id,
         String title,
         String description,
         Long user_id,
         Double user_price,
         String brand,
         String code_fipe,
         String fuel,
         String model,
         Integer model_year,
         Double fipe_price,
         String reference_month,
         LocalDateTime created_at
) {
    public AdRequestDTO(Ads ads) {
        this(
                ads.getId(),
                ads.getTitle(),
                ads.getDescription(),
                ads.getUser_id().getId(),
                ads.getUser_price(),
                ads.getBrand(),
                ads.getCode_fipe(),
                ads.getFuel(),
                ads.getModel(),
                ads.getModel_year(),
                ads.getFipe_price(),
                ads.getReference_month(),
                ads.getCreated_at()
        );
    }
}
