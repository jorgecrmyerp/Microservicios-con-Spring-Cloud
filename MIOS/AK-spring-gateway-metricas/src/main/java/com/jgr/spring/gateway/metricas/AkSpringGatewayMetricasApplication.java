package com.jgr.spring.gateway.metricas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Class AkSpringGatewayMetricasApplication.
 * ESTE ES CLIENTE DE EUREKA Y NECESITA TAMBIEN EL CONFIGURACION CLOUD SERVER
 * PARA USAR CON FAILOVER,SUPERHERO Y YODA
 *Metricas de los microservicios
 */
@SpringBootApplication
public class AkSpringGatewayMetricasApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AkSpringGatewayMetricasApplication.class, args);
	}

}
