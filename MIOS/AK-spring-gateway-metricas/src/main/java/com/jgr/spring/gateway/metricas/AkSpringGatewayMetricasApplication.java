package com.jgr.spring.gateway.metricas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Class AkSpringGatewayMetricasApplication.
 * ESTE ES CLIENTE DE EUREKA Y NECESITA TAMBIEN EL CONFIGURACION CLOUD SERVER
 * PARA USAR CON FAILOVER,SUPERHERO Y YODA
 *Metricas de los microservicios
 *incluimos PROMETHEUS como starter
 *localhost:8082/actuator/metrics/http.server.requests?tag=uri:/yoda&tag=status:200->sacaria las llamadas al 
 *endpoint yoda que han ido bien
 *
 *localhost:9090/actuator/metrics/jvm.classes.loaded->saca las clases que estan cargadas en la jvm
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
