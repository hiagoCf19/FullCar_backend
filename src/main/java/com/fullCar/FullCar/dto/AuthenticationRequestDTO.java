package com.fullCar.FullCar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record AuthenticationRequestDTO(
        @Schema(example ="teste@gmail.com")
        @Email
        String email,
        @Schema(example = "123456")
        @NotNull
        String password) {
}
