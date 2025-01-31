package com.coderhouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class ProyectoFinalFacturacion{
	
	
	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalFacturacion.class, args);
	}
	
	@Bean 
	RestTemplate restTemplate() {
		return new RestTemplate();	
	}
	
}
