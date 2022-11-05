package com.jgr.spring.gateway.zipkin.sleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Class AjSpringGatewayZipkinSleuthApplication.
 * ESTE ES CLIENTE DE EUREKA Y NECESITA TAMBIEN EL CONFIGURACION CLOUD SERVER
 * PARA USAR CON FAILOVER,SUPERHERO Y YODA
 * uso de zipkin y sleuth para hacer un trace de las llamadas
 */
@SpringBootApplication
public class AjSpringGatewayZipkinSleuthApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AjSpringGatewayZipkinSleuthApplication.class, args);
	}

}
