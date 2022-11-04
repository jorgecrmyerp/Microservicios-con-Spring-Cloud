package com.jgr.micro.superhero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AhMicroservicioYodaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AhMicroservicioYodaApplication.class, args);
	}

}
