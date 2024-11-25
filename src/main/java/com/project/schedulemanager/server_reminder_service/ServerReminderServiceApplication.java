package com.project.schedulemanager.server_reminder_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ServerReminderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerReminderServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			// Your logic goes here
			System.out.println("Spring Boot application has started!");
			// Example: Initialize or check database connections, load initial data, etc.
		};
	}

}
