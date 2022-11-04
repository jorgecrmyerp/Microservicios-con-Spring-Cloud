package com.jgr.cliente.feign.loadbalancer.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jgr.cliente.feign.loadbalancer.CloudClientConfig;
import com.jgr.cliente.feign.loadbalancer.feign.client.LoadBalancerFeignClient;
import com.netflix.discovery.EurekaClient;

/**
 * The Class ClienteEurekaController
 * 
 * 
 * conecta con los metodos de aa-basico
 */

@RequestMapping("/feign-client")
@RestController
public class ClienteEurekaController {

	@Autowired
	private CloudClientConfig cloudClientConfig;
	@Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
	private LoadBalancerFeignClient aabasicoFeignClient;
	
	
	
	@GetMapping("/juegodetronos")
	public ResponseEntity<?> juegoDeTronos(){
				
		
		return ResponseEntity.ok().body(aabasicoFeignClient.getCharactersGameOfThrones());
	}
	@GetMapping("/chucknorris")
	public ResponseEntity<?> chuckNorris(){
		
		
		return ResponseEntity.ok().body(aabasicoFeignClient.getCharactersChuck());
	}
	
	@GetMapping("/yoda")
	public ResponseEntity<?> yoda(){
		
		
		return ResponseEntity.ok().body(aabasicoFeignClient.getCharactersYoda());
	}
	@GetMapping("/witcher")
	public ResponseEntity<?> witcher(){
		
		
		return ResponseEntity.ok().body(aabasicoFeignClient.getCharactersWitcher());
	}
	
	
	
}
