package com.jgr.micro.yoda.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;

@RestController
@RequestMapping("/gameof")
public class GameOfThronesController {

	/** The faker. */
	private Faker faker;

	/** The characters game. */
	private List<String> charactersGame;

	/** The characters game. */

	@GetMapping("/juegotronos")
	public ResponseEntity<?> getCharactersGameOfThrones() {
		System.out.println("**************GameOfThronesController***********************");

		charactersGame = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			faker = new Faker();

			charactersGame.add(faker.gameOfThrones().dragon());

		}

		return ResponseEntity.ok(charactersGame);

	}
	@GetMapping("/yoda")
	public ResponseEntity<?> getCharactersOtro() {
		
		charactersGame = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			faker = new Faker();
			
			charactersGame.add(faker.beer().name());
			
		}
		
		return ResponseEntity.ok(charactersGame);
		
	}

}
