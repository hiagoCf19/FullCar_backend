package com.fullCar.FullCar.dto;

import com.fullCar.FullCar.model.Ads;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


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
         BigDecimal user_price,
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
         BigDecimal fipe_price,
         @NotNull
         String reference_month,
         @NotNull
         BigDecimal kilometers_driven,
         @NotNull
         String type_of_vehicle,
         @NotNull
         String traffic_signs,
         @NotNull
         String car_color,
         @NotNull
         String type_of_direction,
         @NotNull
         String gear_box,
         @NotNull
         String engine_power
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
                ads.getKilometers_driven(),
                ads.getType_of_vehicle(),
                ads.getTraffic_signs(),
                ads.getCar_color(),
                ads.getType_of_direction(),
                ads.getGear_box(),
                ads.getEngine_power()
        );
    }
}
