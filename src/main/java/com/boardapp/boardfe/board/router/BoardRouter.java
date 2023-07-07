package com.boardapp.boardfe.board.router;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class BoardRouter {

    @Bean
    public RouterFunction<ServerResponse> htmlRouter(
        // @Value("classpath:/static/css/style.css") Resource html
        @Value("classpath:/templates/board/layout/basic.html") Resource html
    ) {
        return RouterFunctions.route(RequestPredicates.GET("/test"),req -> ServerResponse.ok().contentType(MediaType.TEXT_HTML).bodyValue(html));
    }
}
