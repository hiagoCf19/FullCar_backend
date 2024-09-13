package com.fullCar.FullCar.controller;

import com.fullCar.FullCar.service.ConsumerFIPEService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fipe")
@RequiredArgsConstructor
public class ConsumerFIPEController {
    private final ConsumerFIPEService consumerFIPEService;

    @GetMapping("/marcas")
    public String getMarcas() {
        return consumerFIPEService.getMarcas();
    }
    @GetMapping("/marcas/{marcaCodigo}/modelos")
    public String getModelos(@PathVariable String marcaCodigo) {
        return consumerFIPEService.getModelos(marcaCodigo);
    }
}
