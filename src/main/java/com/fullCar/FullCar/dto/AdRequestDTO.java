package com.fullCar.FullCar.dto;

import com.fullCar.FullCar.model.Ads;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public record AdRequestDTO(
         Long id,
         @NotNull
         @NotBlank
         String title,
         @NotNull
         @NotBlank
         String description,
         @NotNull
         Long user_id,
         @NotNull
         Double user_price,
         @NotNull
         @NotBlank
         String brand,
         @NotNull
         String code_fipe,
         @NotNull
         @NotBlank
         String fuel,
         @NotNull
         String model,
         @NotNull
         Integer model_year,
         @NotNull
         Double fipe_price,
         @NotNull
         String reference_month

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
                ads.getReference_month()

        );
    }
}
