package com.fullCar.FullCar.controller;

import com.fullCar.FullCar.dto.VehicleBrandResponseDTO;
import com.fullCar.FullCar.service.ConsumerFIPEService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fipe")
@RequiredArgsConstructor
public class ConsumerFIPEController {
    private final ConsumerFIPEService consumerFIPEService;

    @GetMapping("/marcas")
    public ResponseEntity<List<VehicleBrandResponseDTO>> getMarcas() {
        // Obtém a lista de marcas do serviço
        List<VehicleBrandResponseDTO> marcas = consumerFIPEService.getMarcas();

        // Retorna a lista dentro de um ResponseEntity
        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/{marcaNome}")
    public ResponseEntity<String> getModelos(@PathVariable String marcaNome) {
        try {
            String modelos = consumerFIPEService.getCodigoByNome(marcaNome);
            return ResponseEntity.ok().body(modelos);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Marca não encontrada: " + marcaNome);
        }
    }

    @GetMapping("/{brandName}/{modeloCodigo}")
    public String getInfoModel(@PathVariable String brandName, @PathVariable String modeloCodigo) {
        return consumerFIPEService.getInfoModel(brandName, modeloCodigo);
    }

    @GetMapping("/{brandName}/{modeloCodigo}/{year}")
    public String getFinalInfo(@PathVariable String brandName, @PathVariable String modeloCodigo, @PathVariable String year) {
        return consumerFIPEService.getYear(brandName, modeloCodigo, year);
    }
}
