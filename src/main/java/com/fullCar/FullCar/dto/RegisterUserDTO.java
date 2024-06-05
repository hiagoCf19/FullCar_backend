package com.fullCar.FullCar.dto;

import java.time.LocalDateTime;

public record RegisterUserDTO(Long id,
         String username,
         String email,
         String password_hash,
         LocalDateTime created_at){}
