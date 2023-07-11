package com.boardapp.boardfe.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class WebClientConfig {
        @Bean
        public WebClient getWebClient() {
                return WebClient.builder()
                                .baseUrl("http://localhost:8000")
                                .filter(ExchangeFilterFunction.ofRequestProcessor(
                                        clientRequest -> {
                                                log.info("==== Client Request  ====");
                                                
                                                log.info("Request : {} {}",clientRequest.method(),clientRequest.url());
                                                
                                                clientRequest.headers()
                                                                .forEach((name,values) -> values.forEach(value -> log.info("{} : {}",name,value)));

                                                return Mono.just(clientRequest);
                                        }
                                ))
                                .filter(ExchangeFilterFunction.ofResponseProcessor(
                                        clientResponse -> {
                                                log.info("==== Client Response ====");

                                                clientResponse.headers()
                                                                .asHttpHeaders()
                                                                .forEach((name,values) -> values.forEach(value -> log.info("{} : {}",name,value)));
                                                
                                                return Mono.just(clientResponse);
                                        }
                                ))
                                .build();
                // return WebClient.create("http://localhost:8000");
        }
}
