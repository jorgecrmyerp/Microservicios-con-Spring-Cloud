package com.jgr.micro.superhero.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AhMicroservicioSuperheroApplication {

	public static void main(String[] args) {
		SpringApplication.run(AhMicroservicioSuperheroApplication.class, args);
	}

}
