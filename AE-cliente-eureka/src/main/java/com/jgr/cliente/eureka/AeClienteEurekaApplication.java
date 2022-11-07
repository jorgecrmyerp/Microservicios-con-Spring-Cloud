package com.jgr.cliente.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// TODO: Auto-generated Javadoc
/**
 * The Class AeClienteEurekaApplication.
 * 
 * 
 * conecta con los metodos de aa-basico
 */
 
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class AeClienteEurekaApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AeClienteEurekaApplication.class, args);
	}

}
