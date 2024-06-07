package com.fullCar.FullCar.dto;

import java.time.LocalDateTime;

public record RegisterAccountDTO(Long id,
                                 String email,
                                 String user_name,
                                 String password_hash,
                                 LocalDateTime created_at){}
