package com.fullCar.FullCar.dto;


import java.time.LocalDateTime;

public record AccountResponseDTO(
        Long id,

        String email,
        LocalDateTime created_at) {
}
