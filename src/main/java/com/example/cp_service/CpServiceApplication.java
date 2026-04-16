package com.example.cp_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class CpServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CpServiceApplication.class, args);
	}

}
