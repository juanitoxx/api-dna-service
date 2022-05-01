package com.co.mercadolibre.infrastructure.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {
		"com.co.mercadolibre.modules", 
		"com.co.mercadolibre.infrastructure",
		"com.co.mercadolibre.crosscutting"
})
@EntityScan(basePackages = "com.co.mercadolibre.crosscutting.persistence.entity")
public class ApiDnaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDnaServiceApplication.class, args);
	}

}
