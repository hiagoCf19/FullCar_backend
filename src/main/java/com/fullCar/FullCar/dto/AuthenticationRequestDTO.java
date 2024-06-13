package com.fullCar.FullCar.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record AuthenticationRequestDTO(
        @Email
        String email,
        @NotNull
        String password) {
}
