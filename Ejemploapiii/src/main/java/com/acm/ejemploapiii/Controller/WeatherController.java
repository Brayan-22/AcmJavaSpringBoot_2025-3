package com.acm.ejemploapiii.Controller;

import com.acm.ejemploapiii.Services.WeatherService;
import com.acm.ejemploapiii.models.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public Mono<WeatherResponse> getWeather(@RequestParam String city) {
        return weatherService.getWeatherByCity(city);
    }

}
