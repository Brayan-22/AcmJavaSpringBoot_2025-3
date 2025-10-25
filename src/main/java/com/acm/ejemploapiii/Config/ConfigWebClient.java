package com.acm.ejemploapiii.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ConfigWebClient {

    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .baseUrl("https://api.openweathermap.org/data/2.5")
                .build();
    }

}
