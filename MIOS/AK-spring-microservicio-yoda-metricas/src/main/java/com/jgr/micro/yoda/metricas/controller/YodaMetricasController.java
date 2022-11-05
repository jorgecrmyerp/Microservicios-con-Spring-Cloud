package com.jgr.micro.yoda.metricas.controller;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;
import com.jgr.micro.yoda.metricas.feign.client.SuperHeroClientFeign;

import brave.Span;
import brave.Tracer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;



/**
 * The Class YodaMetricasController.
 */
@RestController

//@RequestMapping("/yoda")
public class YodaMetricasController {

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
	
	@Autowired
	private ServletWebServerApplicationContext webServerAppCtxt;
	
	/** The instance id. */
	@Value("${eureka.instance.instance-id}")
	private String instanceId;
	
	
	
	
	private static final Logger log =LoggerFactory.getLogger(YodaMetricasController.class);
	



	/**
	 * Gets the characters yoda.
	 *
	 * @return the characters yoda
	 */
	@GetMapping(value = { "/", "", " ", "/yoda" })
	public ResponseEntity<?> getCharactersYoda() {
			
	

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
		System.out.println("alternativaError" + e.getLocalizedMessage() 
		+e.getMessage());		
		
		
		return  superHeroClientFeign.getCharactersFailOver();
		
		//return (Iterable<String>) superHeroClientFeign.getCharactersAlternativa().getBody();
	}

}
