package com.fullCar.FullCar.dto;


import com.fullCar.FullCar.model.Account;
import com.fullCar.FullCar.model.Ads;

import java.time.LocalDateTime;

public record AccountResponseDTO(
        Long id,
        String user_name,
        String email,
        LocalDateTime created_at) {
    public AccountResponseDTO(Account acc) {
        this(
                acc.getId(),
                acc.getUser_name(),
                acc.getEmail(),
                acc.getCreated_at()
        );
    }
}
