package com.example.Clima_RestApi.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.example"})
@EnableMongoRepositories("com.example.Clima_RestApi.repository")
public class ClimaRestApiApplication {
	public static void main(String[] args) {

		SpringApplication.run(ClimaRestApiApplication.class, args);
	}
}