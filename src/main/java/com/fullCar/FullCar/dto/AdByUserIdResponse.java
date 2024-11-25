package com.fullCar.FullCar.dto;

import com.fullCar.FullCar.model.Account;
import com.fullCar.FullCar.model.Ads;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record AdByUserIdResponse(
        Long id,
        String title,
        String description,
        BigDecimal user_price,
        String brand,
        String code_fipe,
        String fuel,
        String model,
        Integer model_year,
        BigDecimal fipe_price,
        String reference_month,
        LocalDateTime created_at,
        BigDecimal kilometers_driven,
        String type_of_vehicle,
        String traffic_signs,
        String car_color,
        String type_of_direction,
        String gear_box,
        String engine_power,
        List<ImageResponseDTO> images
) {
    public AdByUserIdResponse(Ads ads, List<ImageResponseDTO> images) {
        this(
                ads.getId(),
                ads.getTitle(),
                ads.getDescription(),
                ads.getUser_price(),
                ads.getBrand(),
                ads.getCode_fipe(),
                ads.getFuel(),
                ads.getModel(),
                ads.getModel_year(),
                ads.getFipe_price(),
                ads.getReference_month(),
                ads.getCreated_at(),
                ads.getKilometers_driven(),
                ads.getType_of_vehicle(),
                ads.getTraffic_signs(),
                ads.getCar_color(),
                ads.getType_of_direction(),
                ads.getGear_box(),
                ads.getEngine_power(),
                images
        );
    }
}
