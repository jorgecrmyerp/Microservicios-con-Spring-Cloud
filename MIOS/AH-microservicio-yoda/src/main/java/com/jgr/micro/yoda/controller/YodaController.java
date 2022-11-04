package com.jgr.micro.yoda.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;

@RestController
@RequestMapping("/yoda")
public class YodaController {

	/** The faker. */
	private Faker faker;

	/** The characters game. */
	private List<String> charactersYoda;

	@GetMapping(value = { "", "/" })
	public ResponseEntity<?> getCharactersYoda() {

		charactersYoda = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			faker = new Faker();

			charactersYoda.add(faker.yoda().quote());

		}

		return ResponseEntity.ok(charactersYoda);

	}

}
