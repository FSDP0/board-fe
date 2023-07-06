package com.boardapp.boardfe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class BoardFeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardFeApplication.class, args);
	}

}
