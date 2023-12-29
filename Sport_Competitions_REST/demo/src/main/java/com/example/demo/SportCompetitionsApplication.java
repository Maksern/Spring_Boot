package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
	info = @Info(
		title = "Sport Competitions Demo",
		contact = @Contact(
			name = "Team 4",
			email = "maks.men9922@gmail.com"
		),
		description = "This site, where you can see sport competitions from all world",
		version = "1.0.0"
	),

	servers = {
		@Server(url = "http://localhost:8080", description = "test server"),
	}
)

@EnableTransactionManagement
@SpringBootApplication
public class SportCompetitionsApplication {
	public static void main(String[] args) {
		SpringApplication.run(SportCompetitionsApplication.class, args);
	}
}

