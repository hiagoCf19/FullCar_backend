package com.fullCar.FullCar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

public record AccountRequestDTO(

        @Email
        @Schema(example = "teste@gmail.com")
        String email,

        @Schema(example = "test user")
        @NotNull
        @NotBlank
        String user_name,

        @Schema(example = "123456")
        @NotNull
        String password,

        @Schema(example = "31988888888")
        @NotNull
        String phone,

        @Schema(example = "31192458")
        @NotNull
        @NumberFormat
        Long cep,

        @Schema(example = "street A")
        @NotNull
        @Size(min=1, message = "street required")
        String street,

        @Schema(example = "neighborhood A")
        @NotNull
        @Size(min=1, message = "neighborhood required")
        String neighborhood,

        @Schema(example = "city A")
        @NotNull
        @Size(min=1, message = "city required")
        String city,

        @Schema(example = "AB")
        @NotNull
        @Size(min = 2, max = 2, message = "UF must have exactly 2 characters")
        String uf
        ){}
