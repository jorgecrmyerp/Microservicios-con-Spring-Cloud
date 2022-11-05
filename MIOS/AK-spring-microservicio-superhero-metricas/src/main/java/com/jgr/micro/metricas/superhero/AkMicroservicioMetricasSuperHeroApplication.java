package com.jgr.micro.metricas.superhero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AkMicroservicioMetricasSuperHeroApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkMicroservicioMetricasSuperHeroApplication.class, args);
	}

}
