package com.acm.ejemploapiii.Services;

import com.acm.ejemploapiii.models.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {

    private final WebClient webClient;

    @Value("${openweather.api.key}")
    private String apiKey;

    @Autowired
    public WeatherService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<WeatherResponse> getWeatherByCity(String city) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/weather")
                        .queryParam("q", city)
                        .queryParam("appid", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(WeatherResponse.class);
    }


}
