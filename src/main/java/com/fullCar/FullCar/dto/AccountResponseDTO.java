package com.fullCar.FullCar.dto;


import com.fullCar.FullCar.model.Account;

import java.time.LocalDateTime;

public record AccountResponseDTO(
        Long id,
        String user_name,
        String email,
        LocalDateTime created_at,
        Boolean is_confirmed
) {
    public AccountResponseDTO(Account acc) {
        this(
                acc.getId(),
                acc.getUser_name(),
                acc.getEmail(),
                acc.getCreated_at(),
                acc.getIs_confirmed()
        );
    }
}
