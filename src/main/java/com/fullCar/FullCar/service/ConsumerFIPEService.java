package com.fullCar.FullCar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullCar.FullCar.dto.VehicleBrandResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumerFIPEService {
    private static final String BASE_URL = "https://parallelum.com.br/fipe/api/v1/carros";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public List<VehicleBrandResponseDTO> getMarcas() {
        String url = BASE_URL + "/marcas";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<VehicleBrandResponseDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<VehicleBrandResponseDTO>>() {}
        );

        return response.getBody();
    }

    // Método para buscar o código da marca com base no nome
    public String getCodigoByNome(String nomeMarca) {
        var brand= transformNameInCodeBrand(nomeMarca);
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment("marcas", brand, "modelos")
                .toUriString();
        return restTemplate.getForObject(url, String.class);
    }

    public String getInfoModel(String brandName, String modeloCodigo) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment("marcas", transformNameInCodeBrand(brandName), "modelos", modeloCodigo, "anos")
                .toUriString();
        return restTemplate.getForObject(url, String.class);
    }
    // Método para buscar o ano e informações baseadas na marca, modelo e ano
    public String getYear(String brandName, String modeloCodigo, String ano) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment("marcas", transformNameInCodeBrand(brandName), "modelos", modeloCodigo, "anos", ano)
                .toUriString();
        return restTemplate.getForObject(url, String.class);
    }
    private String transformNameInCodeBrand(String nomeMarca){
        List<VehicleBrandResponseDTO> marcas = getMarcas();
        // Resgatar o código da marca pelo nome
        return  marcas.stream()
                .filter(marca -> marca.nome().equalsIgnoreCase(nomeMarca)) // Compara o nome da marca (ignorando case)
                .findFirst()
                .map(VehicleBrandResponseDTO::codigo) // Pega o código correspondente
                .orElseThrow(() -> new RuntimeException("Marca não encontrada: " + nomeMarca)); // Exceção se não encontrar
        //Buscar na api com o código resgatado
    }


    //URL final ex: https://parallelum.com.br/fipe/api/v1/carros/marcas/59/modelos/5940/anos/2011-3
}
