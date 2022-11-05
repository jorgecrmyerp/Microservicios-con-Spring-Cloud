package com.jgr.micro.failover.superhero.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;

@RestController
//@RequestMapping("/failover")
public class FailOverController {

	/** The faker. */
	private Faker faker;

	/** The characters game. */
	private List<String> charactersFailOver;

	@GetMapping(value = { "", "/yoda/","/superhero/" })
	public ResponseEntity<?> getCharactersFailOver() {

		charactersFailOver = new ArrayList<>();
		
		charactersFailOver.add("*******************************************************");
		charactersFailOver.add("***EN EL MICROSERVICIO FAILOVERCONTROLLER**************");
		charactersFailOver.add("***EL PROFILE DEL GATEWAY-REDIRECCIONA NO ES noeureka**");
		charactersFailOver.add("*******************************************************");
		
		for (int i = 0; i < 10; i++) {
			faker = new Faker();

			charactersFailOver.add(
					faker.lordOfTheRings().character().concat("->").concat(new Faker().lordOfTheRings().location()));

		}

		return ResponseEntity.ok(charactersFailOver);

	}
	@GetMapping(value = { "/alternativa"})
	public ResponseEntity<?> getCharactersAlternativa() {
		
		charactersFailOver = new ArrayList<>();
		
		charactersFailOver.add("*******************************************************");
		charactersFailOver.add("***EN EL MICROSERVICIO FAILOVERCONTROLLER ALTERNATIVA**");
		charactersFailOver.add("*******************************************************");
		
		for (int i = 0; i < 10; i++) {
			faker = new Faker();
			
			charactersFailOver.add(
					faker.beer().name().concat(faker.beer().style()));
			
		}
		
		return ResponseEntity.ok(charactersFailOver);
		
	}

}
