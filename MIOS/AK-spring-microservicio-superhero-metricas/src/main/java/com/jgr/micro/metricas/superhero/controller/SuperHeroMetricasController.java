package com.jgr.micro.metricas.superhero.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.cloud.config.environment.Environment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;

import brave.Span;
import brave.Tracer;

@RestController
//@RequestMapping("/failover")
public class SuperHeroMetricasController {

	/** The faker. */
	private Faker faker;

	/** The characters game. */
	private List<String> charactersFailOver;

	@Autowired
	private ServletWebServerApplicationContext webServerAppCtxt;
	


	/** The instance id. */
	@Value("${eureka.instance.instance-id}")
	private String instanceId;
	

	@GetMapping(value = { "/", "", " ", "/superhero" })
	public ResponseEntity<?> getCharactersFailOver() {
				
		
		
		String puerto = String.valueOf(webServerAppCtxt.getWebServer().getPort());

		charactersFailOver = new ArrayList<>();

		charactersFailOver.add("*******************************************************");
		charactersFailOver.add("***EN EL MICROSERVICIO FAILOVERCONTROLLER**************");
		charactersFailOver.add("*******************************************************");

		for (int i = 0; i < 10; i++) {
			faker = new Faker();

			charactersFailOver.add("Puerto->".concat(puerto).concat(" Instancia->")
					.concat(instanceId).concat("->")
					.concat(faker.lordOfTheRings().character().concat("->")
							.concat(new Faker().lordOfTheRings().location())));

		}

		return ResponseEntity.ok(charactersFailOver);

	}

	@GetMapping(value = { "/alternativa" })
	public ResponseEntity<?> getCharactersAlternativa() {
	
	
		charactersFailOver = new ArrayList<>();

		charactersFailOver.add("*******************************************************");
		charactersFailOver.add("***EN EL MICROSERVICIO FAILOVERCONTROLLER ALTERNATIVA**");
		charactersFailOver.add("*******************************************************");

		for (int i = 0; i < 10; i++) {
			faker = new Faker();

			charactersFailOver.add(faker.beer().name().concat(faker.beer().style()));

		}

		return ResponseEntity.ok(charactersFailOver);

	}

}
