package com.boardapp.boardfe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients	// ! Enable Feign Clients annotation for using OpenFeign
@SpringBootApplication
public class BoardFeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardFeApplication.class, args);
	}

}
