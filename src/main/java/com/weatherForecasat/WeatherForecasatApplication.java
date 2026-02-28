package com.weatherForecasat;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.TimeUnit;

@EnableCaching
@SpringBootApplication
public class WeatherForecasatApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherForecasatApplication.class, args);
	}

	@Bean
	public WebClient webClient() {
		return WebClient.builder()
				.baseUrl("https://api.openweathermap.org")
				.build();
	}

	@Bean
	public CaffeineCacheManager cacheManager() {
		CaffeineCacheManager cacheManager =
				new CaffeineCacheManager("weatherCache");

		cacheManager.setCaffeine(
				Caffeine.newBuilder()
						.expireAfterWrite(10, TimeUnit.MINUTES) // TTL
						.maximumSize(100)                      // max entries
		);

		return cacheManager;
	}
}
