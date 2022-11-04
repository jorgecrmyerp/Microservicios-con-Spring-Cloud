package com.jgr.spring.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: Auto-generated Javadoc
/**
 * The Class AgSpringGatewayApplication.
 * ESTE ES CLIENTE DE EUREKA
 * PARA USAR CON FAILOVER,SUPERHERO Y YODA
 * REVISAR EL GATEWAYCONFIGURATION, LO REDIRIJO A UNA RUTA U OTRA DEPENDIENDO DE LA URI
 * 
 */
@SpringBootApplication
public class AgSpringGatewayApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AgSpringGatewayApplication.class, args);
	}

}
