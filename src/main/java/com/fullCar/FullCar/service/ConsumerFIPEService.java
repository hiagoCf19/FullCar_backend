package com.fullCar.FullCar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class ConsumerFIPEService {
    private static final String BASE_URL = "https://parallelum.com.br/fipe/api/v1/carros";
    private final RestTemplate restTemplate;

    public String getMarcas() {
        String url = BASE_URL + "/marcas";
        return restTemplate.getForObject(url, String.class);
    }
    //irá receber a base url com os códigos que já tem e só completar a informação que falta
    public String getModelos(String marcaCodigo) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment("marcas", marcaCodigo, "modelos")
                .toUriString();
        return restTemplate.getForObject(url, String.class);
    }
}
