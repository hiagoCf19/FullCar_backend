package com.fullCar.FullCar.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AccountRequestDTO(

        Long id,
        @Email
        String email,
        @NotNull
        @NotBlank
        String user_name,
        @NotNull
        String password,
        LocalDateTime created_at){}
