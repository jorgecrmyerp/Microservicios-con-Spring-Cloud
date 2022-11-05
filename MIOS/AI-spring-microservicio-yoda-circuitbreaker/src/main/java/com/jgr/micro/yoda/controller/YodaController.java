package com.jgr.micro.yoda.controller;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;
import com.jgr.micro.yoda.feign.client.SuperHeroClientFeign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;



// TODO: Auto-generated Javadoc
/**
 * The Class YodaController.
 */
@RestController

//@RequestMapping("/yoda")
public class YodaController {

	/** The circuit breaker factory. */
	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;

	/** The super hero client feign. */
	@Autowired
	private SuperHeroClientFeign superHeroClientFeign;

	/** The faker. */
	private Faker faker;

	/** The characters game. */
	private List<String> charactersYoda;

	/**
	 * Gets the characters yoda.
	 *
	 * @return the characters yoda
	 */
	@GetMapping(value = { "/", "", " ", "/yoda" })
	public ResponseEntity<?> getCharactersYoda() {
		System.out.println("**************YODA CONTROLLER getCharactersYoda()***********************");

		return (ResponseEntity<?>) circuitBreakerFactory.create("micro-yoda-resiliencia").run(
				() -> ResponseEntity.ok(devuelveCaracteres()), // esto es lo que haria si va bien
				e -> superHeroClientFeign.getCharactersFailOver()) // esto si da error

				;
	}

	/**
	 * Circuit breaker yoda. Redirige a otro metodo en caso de error
	 * 
	 * @return the response entity
	 */
	@CircuitBreaker(name = "micro-yoda-resiliencia",fallbackMethod="alternativaError")
	@GetMapping(value = { "/yoda/circuit" })	
	public ResponseEntity<?> circuitBreakerYoda() {
		System.out.println("**************YODA CONTROLLER  circuitBreakerYoda() ***********************");

		return ResponseEntity.ok(devuelveCaracteres());
	}

	/**
	 * Devuelve caracteres.
	 *
	 * @return the iterable
	 */
	private Iterable<String> devuelveCaracteres() {
		charactersYoda = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			faker = new Faker();
			charactersYoda.add(faker.yoda().quote());

			if (i >= 5) {
				throw new EmptyStackException();
			}

		}

		return charactersYoda;

	}

	/**
	 * Alternativa error.
	 * IMPORTANTE QUE TENGA EL THROWABLE,SI NO DA ERROR
	 *
	 * @return the response entity
	 */
	private ResponseEntity<?> alternativaError(Throwable e) {
		System.out.println("dentro del metodo alternativa error"+ e.getLocalizedMessage() 
		+e.getMessage());
		
		
		return  superHeroClientFeign.getCharactersFailOver();
		
		//return (Iterable<String>) superHeroClientFeign.getCharactersAlternativa().getBody();
	}

}
