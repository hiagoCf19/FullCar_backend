package com.fullCar.FullCar.dto;

import com.fullCar.FullCar.model.Ads;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;


public record AdRequestDTO(
        @Schema(description = "ID da solicitação", example = "12345")
        Long id,

        @NotNull
        @NotBlank
        @Schema(description = "Título do anúncio", example = "Carro usado em bom estado")
        String title,

        @NotNull
        @NotBlank
        @Schema(description = "Descrição detalhada do anúncio", example = "Carro de 2019, único dono, sem acidentes")
        String description,

        @NotNull
        @Schema(description = "ID do usuário responsável pelo anúncio", example = "56789")
        Long user_id,

        @NotNull
        @DecimalMin(value = "0.00", inclusive = true)
        @Schema(description = "Preço solicitado pelo usuário", example = "15000.00")
        BigDecimal user_price,

        @NotNull
        @NotBlank
        @Schema(description = "Marca do veículo", example = "Toyota")
        String brand,

        @NotNull
        @NotBlank
        @Schema(description = "Código FIPE do veículo", example = "123456789")
        String code_fipe,

        @NotNull
        @NotBlank
        @Schema(description = "Tipo de combustível", example = "Gasolina")
        String fuel,

        @NotNull
        @NotBlank
        @Schema(description = "Modelo do veículo", example = "Corolla")
        String model,

        @NotNull
        @Min(1900)
        @Max(2025)
        @Schema(description = "Ano do modelo do veículo", example = "2019")
        Integer model_year,

        @NotNull
        @DecimalMin(value = "0.00", inclusive = true)
        @Schema(description = "Preço FIPE do veículo", example = "14000.00")
        BigDecimal fipe_price,

        @NotNull
        @NotBlank
        @Schema(description = "Mês de referência para o valor FIPE", example = "02/2025")
        String reference_month,

        @NotNull
        @DecimalMin(value = "0.00", inclusive = true)
        @Schema(description = "Quilometragem rodada do veículo", example = "30000.00")
        BigDecimal kilometers_driven,

        @NotNull
        @NotBlank
        @Schema(description = "Tipo de veículo", example = "Sedan")
        String type_of_vehicle,

        @NotNull
        @NotBlank
        @Size(min = 1, max = 10)
        @Schema(description = "Sinalizações de trânsito que o veículo tem", example = "BRASIL")
        String traffic_signs,

        @NotNull
        @NotBlank
        @Schema(description = "Cor do veículo", example = "Preto")
        String car_color,

        @NotNull
        @NotBlank
        @Schema(description = "Tipo de direção", example = "Hidráulica")
        String type_of_direction,

        @NotNull
        @NotBlank
        @Schema(description = "Tipo de câmbio", example = "Manual")
        String gear_box,

        @NotNull
        @NotBlank
        @Schema(description = "Potência do motor", example = "150")
        String engine_power
) {
    public AdRequestDTO(Ads ads) {
        this(
                ads.getId(),
                ads.getTitle(),
                ads.getDescription(),
                ads.getUser_id().getId(),
                ads.getUser_price(),
                ads.getBrand(),
                ads.getCode_fipe(),
                ads.getFuel(),
                ads.getModel(),
                ads.getModel_year(),
                ads.getFipe_price(),
                ads.getReference_month(),
                ads.getKilometers_driven(),
                ads.getType_of_vehicle(),
                ads.getTraffic_signs(),
                ads.getCar_color(),
                ads.getType_of_direction(),
                ads.getGear_box(),
                ads.getEngine_power()
        );
    }
}
