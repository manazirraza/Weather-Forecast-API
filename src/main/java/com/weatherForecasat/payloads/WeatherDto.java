package com.weatherForecasat.payloads;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDto {

    private String city;
    private String country;
    private double temperature;
    private int humidity;
    private String description;
    private double windSpeed;

}
