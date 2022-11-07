package com.jgr.spring.gateway.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Class ALSpringGatewayDockerApplication.
 * ESTE ES CLIENTE DE EUREKA Y NECESITA TAMBIEN EL CONFIGURACION CLOUD SERVER
 * AL INSTALAR CON MAVEN EL PROGRAMA SE CREA LA IMAGEN Y EL CONTENEDOR
 * TIENE QUE ESTARSE EJECUTANDO DOCKER CUANDO SE GENERA
 * 
*/
@SpringBootApplication
public class ALSpringGatewayDockerApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ALSpringGatewayDockerApplication.class, args);
	}

}
