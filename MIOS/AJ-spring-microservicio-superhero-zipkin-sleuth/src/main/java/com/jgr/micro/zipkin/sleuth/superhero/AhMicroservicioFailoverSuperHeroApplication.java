package com.jgr.micro.zipkin.sleuth.superhero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AhMicroservicioFailoverSuperHeroApplication {

	public static void main(String[] args) {
		SpringApplication.run(AhMicroservicioFailoverSuperHeroApplication.class, args);
	}

}
