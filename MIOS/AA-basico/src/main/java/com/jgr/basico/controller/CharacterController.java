package com.jgr.basico.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.github.javafaker.Faker;
import com.jgr.basico.error.CharacterNotFound;

/**
 * The Class CharacterController.
 * el ResponseStatusException (buscaCharactersGameOfThrones) es mejor si la aplicacion es nueva
 * ,para usar las existentes usariamos el throw normal, como en  buscaCharactersByPrefix-CharacterNotFound
 */
@RequestMapping("/characters")
@RestController
public class CharacterController {

	// clase faker que genera string aleatorios
	// https://github.com/DiUS/java-faker
	// https://www.baeldung.com/java-faker

	/** The faker. */
	private Faker faker;

	/** The characters chuck. */
	private List<String> charactersChuck;

	/** The characters beer. */
	private List<String> charactersBeer;

	/** The characters game. */
	private List<String> charactersGame;

	/**
	 * Inicio. carga las listas con los datos
	 */
	@PostConstruct
	public void inicio() {
		charactersChuck = new ArrayList<>();
		charactersBeer = new ArrayList<>();
		charactersGame = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			faker = new Faker();

			charactersBeer.add(faker.beer().name().concat(" ").concat(faker.beer().yeast()));
			charactersChuck.add(faker.chuckNorris().fact());
			charactersGame.add(faker.gameOfThrones().dragon());

		}

	}

	/**
	 * Gets the characters chuck.
	 *
	 * @return the characters chuck
	 */
	@GetMapping("/chucknorris")
	public ResponseEntity<?> getCharactersChuck() {

		return ResponseEntity.ok(charactersChuck);

	}

	/**
	 * Gets the characters beer.
	 *
	 * @return the characters beer
	 */
	@GetMapping("/cerveza")
	public ResponseEntity<?> getCharactersBeer() {
		return ResponseEntity.ok(charactersBeer);

	}

	/**
	 * Gets the characters game of thrones.
	 *
	 * @return the characters game of thrones
	 */
	@GetMapping("/juegotronos")
	public ResponseEntity<?> getCharactersGameOfThrones() {

		return ResponseEntity.ok(charactersGame);

	}

	/**
	 * Busca characters Path Variable en la lista de game of thrones.
	 * cuando buscamos solo un elemento¿?
	 * localhost:8080/characters/juegotronos/Westbrook
	 * @param name the name
	 * @return the string
	 */
	@GetMapping("/juegotronos/{name}")
	public String buscaCharactersGameOfThrones(@PathVariable("name") String name) {

		return charactersGame.stream().filter(c -> c.equals(name)).findAny().orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("%s no encontrado", name)));

	}
	
	/**
	 * Busca characters by prefix.
	 * para hacer un filtrado, puede haber mas de uno
	 * localhost:8080/characters/juegotronos/search?prefijo=Sun
	 * localhost:8080/characters/juegotronos/search?parametro1=Sun&parametro2=
	 * @param prefijo the prefijo
	 * @return the list
	 */
	@GetMapping("/juegotronos/search")
	public List<String> buscaCharactersByPrefix(@RequestParam("prefijo") String prefijo) {
		
		List<String> resultado = new ArrayList<>();
		resultado= charactersGame.stream().filter(c->c.startsWith(prefijo)).
				collect(Collectors.toList());
		
		if (resultado.isEmpty()) {
			throw new CharacterNotFound();
			
		}
		
		return resultado;
	}

}
