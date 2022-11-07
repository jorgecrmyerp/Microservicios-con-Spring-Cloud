package com.jgr.cliente.eureka.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * The Interface AabasicoFeignClient.
 * conecta con los metodos de aa-basico
 */
@FeignClient(name="aa-basico",url="localhost:8080/characters")
public interface AabasicoFeignClient {
	
	/**
	 * Gets the characters game of thrones.
	 *
	 * @return the characters game of thrones
	 */
	@GetMapping("/juegotronos")	
	public Iterable<String> getCharactersGameOfThrones();
	
	
	/**
	 * Gets the characters chuck.
	 *
	 * @return the characters chuck
	 */
	@GetMapping("/chucknorris")
	public Iterable<String> getCharactersChuck();

}
