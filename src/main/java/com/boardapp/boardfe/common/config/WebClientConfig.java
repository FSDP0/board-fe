package com.boardapp.boardfe.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableWebFlux
public class WebClientConfig {
        @Bean
        public WebClient getWebClient() {
                return WebClient.create("http://localhost:8000");
        }
}
