package com.jgr.micro.yoda.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="micro-superhero-failover")
public interface  SuperHeroClientFeign {

	@GetMapping(value = {"/superhero/" })
	public ResponseEntity<?> getCharactersFailOver(); 
	
	@GetMapping(value = { "/alternativa"})
	public ResponseEntity<?> getCharactersAlternativa();
}
