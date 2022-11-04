package com.jgr.cliente.eureka.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jgr.cliente.eureka.CloudClientConfig;
import com.jgr.cliente.eureka.feign.client.AabasicoFeignClient;
import com.netflix.discovery.EurekaClient;


@RequestMapping("/eureka-client")
@RestController
public class ClienteEurekaController {

	@Autowired
	private CloudClientConfig cloudClientConfig;
	@Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
	private AabasicoFeignClient aabasicoFeignClient;
	
	
	@GetMapping("/propiedades")
	public ResponseEntity<?> getAppName(){
		Map<String,String> propiedades = new HashMap<>();
		
		//propiedades.put("cloudClientConfig.getApplicationName()", cloudClientConfig.getApplicationName());
		propiedades.put("eurekaClient.getEurekaClientConfig().getProxyPort().toString()", eurekaClient.getEurekaClientConfig().getProxyPort().toString());
		propiedades.put("eurekaClient.getEurekaClientConfig().getClientDataAccept().toString()", eurekaClient.getEurekaClientConfig().getClientDataAccept().toString());
		
		
		return ResponseEntity.ok().body(propiedades);
	}
	@GetMapping("/juegodetronos")
	public ResponseEntity<?> juegoDeTronos(){
				
		
		return ResponseEntity.ok().body(aabasicoFeignClient.getCharactersGameOfThrones());
	}
	@GetMapping("/chucknorris")
	public ResponseEntity<?> chuckNorris(){
		
		
		return ResponseEntity.ok().body(aabasicoFeignClient.getCharactersChuck());
	}
	
	
	
}
