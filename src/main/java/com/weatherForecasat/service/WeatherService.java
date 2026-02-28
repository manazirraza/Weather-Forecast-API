package com.weatherForecasat.service;

import com.weatherForecasat.payloads.WeatherDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherService {

    private WebClient webClient;
    private final String api_key="0894b69454e212ea6bd582fc2f5ae283";

    public WeatherService(WebClient webClient) {
        this.webClient = webClient;
    }

    @Cacheable(value = "weatherCache", key = "#city")
    public WeatherDto getWether(String city) {
        System.out.println("Open api calling");
        WeatherApiResponseDto response =webClient
                .get()
                .uri("/data/2.5/weather?q={city}&appid={key}", city, api_key)
                .retrieve()
                .bodyToMono(WeatherApiResponseDto.class)
                .block();


        return new WeatherDto(
                response.getName(),
                response.getSys().getCountry(),
                response.getMain().getTemp() - 273.15,  // Kelvin → Celsius
                response.getMain().getHumidity(),
                response.getWeather().get(0).getDescription(),
                response.getWind().getSpeed()
        );
    }
}
