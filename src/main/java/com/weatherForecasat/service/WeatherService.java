package com.weatherForecasat.service;

import com.weatherForecasat.payloads.WeatherDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class WeatherService {

    private final String api_key="0894b69454e212ea6bd582fc2f5ae283";

    public WeatherDto getWether(String city) {

        String url ="https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+api_key;

        RestTemplate restTemplate = new RestTemplate();
        WeatherApiResponseDto response = restTemplate.getForObject(url, WeatherApiResponseDto.class);

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
