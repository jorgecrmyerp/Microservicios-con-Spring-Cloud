package com.jgr.micro.superhero.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;

@RestController
@RequestMapping("superhero")
public class SuperHeroController {

	/** The faker. */
	private Faker faker;

	/** The characters game. */
	private List<String> charactersSuperhero;

	@GetMapping("/superhero")
	public ResponseEntity<?> getCharactersDune() {

		charactersSuperhero = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			faker = new Faker();

			charactersSuperhero.add(faker.superhero().name()
					.concat(" ")
					.concat(faker.superhero().power()));

		}

		return ResponseEntity.ok(charactersSuperhero);

	}

}
