package com.weatherForecasat.controllers;

import com.weatherForecasat.payloads.WeatherDto;
import com.weatherForecasat.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class Cotroller {

    @Autowired
    private WeatherService service;

    @GetMapping("/{city}")
    public ResponseEntity<WeatherDto> getWeather(@PathVariable String city) {
        return ResponseEntity.ok(service.getWether(city));
    }


}
