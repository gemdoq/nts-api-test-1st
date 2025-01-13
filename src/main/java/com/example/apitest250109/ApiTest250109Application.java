package com.example.apitest250109;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiTest250109Application {

	public static void main(String[] args) {
		SpringApplication.run(ApiTest250109Application.class, args);
	}

}
