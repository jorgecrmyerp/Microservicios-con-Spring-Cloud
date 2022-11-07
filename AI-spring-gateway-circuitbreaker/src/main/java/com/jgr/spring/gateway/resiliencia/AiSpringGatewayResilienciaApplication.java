package com.jgr.spring.gateway.resiliencia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: Auto-generated Javadoc
/**
 * The Class AiSpringGatewayResilienciaApplication.
 * ESTE ES CLIENTE DE EUREKA Y NECESITA TAMBIEN EL CONFIGURACION CLOUD SERVER
 * PARA USAR CON FAILOVER,SUPERHERO Y YODA
 * EN CASO DE ERROR DESDE YODA SE MANDA A UN SERVICIO U OTRO DEPENDIENDO DEL METODO INVOCADO
 * CON  circuitBreakerFactory O CON fallbackMethod="alternativaError"
 */
@SpringBootApplication
public class AiSpringGatewayResilienciaApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AiSpringGatewayResilienciaApplication.class, args);
	}

}
