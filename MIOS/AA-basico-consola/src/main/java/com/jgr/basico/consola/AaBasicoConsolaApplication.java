package com.jgr.basico.consola;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;



@SpringBootApplication
@EnableFeignClients
public class AaBasicoConsolaApplication implements ApplicationRunner {

	@Autowired
	private com.jgr.basico.consola.clients.DragonBallCharacterClient dragonBallClient;

//	@Autowired
//	private EurekaClient eurekaClient;

	private static final Logger log = LoggerFactory.getLogger(AaBasicoConsolaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AaBasicoConsolaApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		for (int i = 0; i < 10; i++) {
			ResponseEntity<String> responseEntity = dragonBallClient.getApplicationName();
			log.info("Status {}", responseEntity.getStatusCode());
			String body = responseEntity.getBody();
			log.info("Body {}", body);
		}
	}

	/**
	 * Implementación de cliente de Eureka
	 */
//	@Override
//	public void run(ApplicationArguments args) throws Exception {
//		Application application = eurekaClient.getApplication("devs4j-dragon-ball");
//		log.info("Application name {}",application.getName());
//		List<InstanceInfo> instances = application.getInstances();
//		for (InstanceInfo instanceInfo : instances) {
//			log.info("Ip address {}:{}",instanceInfo.getIPAddr(), instanceInfo.getPort());
//		}
//	}

}
