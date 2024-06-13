package com.fullCar.FullCar.dto;

import com.fullCar.FullCar.model.Ads;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AdResponseDTO(
        Long id,
        String title,

        String description,

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
    public AdResponseDTO(Ads ads) {
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
                ads.getCreated_at()
        );
    }
}
