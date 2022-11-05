package com.jgr.spring.gateway.resiliencia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Class AiSpringGatewayResilienciaApplication.
 * ESTE ES CLIENTE DE EUREKA Y NECESITA TAMBIEN EL CONFIGURACION CLOUD SERVER
 * PARA USAR CON FAILOVER,SUPERHERO Y YODA
 * REVISAR EL GATEWAYCONFIGURATION, LO REDIRIJO A UNA RUTA U OTRA DEPENDIENDO DE LA URI
 * localhost:9090/yoda/->envia a http://localhost:8087/yoda
 * utilizando el properties,si es noeureka redirecciona a este mismo servicio
 * si en el properties del gateway-redirecciona es otra cosa lo envia al microservicio micro-superhero-failover
 * registrado en eureka 
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
