package com.jgr.config.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jgr.config.client.config.CloudClientConfig;

@RestController
@RequestMapping("/application-name")
public class CloudClientController {
	
	@Autowired
	private CloudClientConfig cloudClientConfig;
	
	
	@GetMapping
	public ResponseEntity<?> getAppName(){
		return ResponseEntity.ok().body(cloudClientConfig.getApplicationName());
	}
	

}
