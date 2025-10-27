package com.acm.apiconnectacm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ConfigWebClient {

    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .baseUrl("https://fakestoreapi.com")
                .build();
    }

    @Bean
    public WebClient webClientPetstore(){
        return WebClient.builder()
                .baseUrl("https://petstore.swagger.io/v2")
                .build();
    }

}
