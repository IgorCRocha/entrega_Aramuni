package com.example.Fipe_RestApi.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example"})
public class RestApiApplication {
	public static void main(String[] args) {

		SpringApplication.run(RestApiApplication.class, args);
	}

}
