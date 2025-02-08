package com.fullCar.FullCar.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record AccountUpdateRequestDTO(
        @NotNull
        Long id,

        String user_name,
        @Email
        String email) {
}
