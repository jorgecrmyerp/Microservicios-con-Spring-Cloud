package com.jgr.micro.yoda.zipkin.sleuth.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="micro-superhero-zipkin-sleuth")
public interface  SuperHeroClientFeign {

	@GetMapping(value = {"/superhero/" })
	public ResponseEntity<?> getCharactersFailOver(); 
	
	@GetMapping(value = { "/alternativa"})
	public ResponseEntity<?> getCharactersAlternativa();
}
