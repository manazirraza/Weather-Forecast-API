package com.weatherForecasat.service;

import lombok.Data;

import java.util.List;

@Data
public class WeatherApiResponseDto {

    private Main main;
    private List<Weather> weather;
    private Wind wind;
    private Sys sys;
    private String name;

    @Data
    public static class Main {
        private double temp;
        private int humidity;
    }

    @Data
    public static class Weather {
        private String description;
    }

    @Data
    public static class Wind {
        private double speed;
    }

    @Data
    public static class Sys {
        private String country;
    }
}
