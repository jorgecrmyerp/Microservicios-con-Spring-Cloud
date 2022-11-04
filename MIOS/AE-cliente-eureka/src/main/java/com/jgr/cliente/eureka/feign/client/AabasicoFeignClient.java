package com.jgr.cliente.eureka.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="aa-basico",url="localhost:8080/characters")
public interface AabasicoFeignClient {
	
	@GetMapping("/juegotronos")	
	public Iterable<String> getCharactersGameOfThrones();
	
	
	@GetMapping("/chucknorris")
	public Iterable<String> getCharactersChuck();

}
