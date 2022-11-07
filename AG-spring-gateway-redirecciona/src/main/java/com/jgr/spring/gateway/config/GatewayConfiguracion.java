package com.jgr.spring.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class GatewayConfiguracion {

	
	private static final Logger log = LoggerFactory.getLogger(GatewayConfiguracion.class);
	//en el properties local si le pongo noeureka la ruta es hardcode,en otro caso va a eureka
	
	
	@Value("${spring.profiles.active}")
	private String perfil;
	
	
	
	
	@Bean
	public RouteLocator configLocalEureka(RouteLocatorBuilder builder) {
		log.debug("EN EUREKA");
		System.out.println("***************EN EUREKA********************");
		System.out.println("perfil eureka->" + perfil);
		return builder.routes().route(ruta -> ruta.path("/yoda/*")// cuando se ponga esto en el path
				.uri("lb://micro-yoda")) // se va aqui
				.route(ruta -> ruta.path("/superhero/*")// cuando se ponga esto en el path
						.uri("lb://micro-superhero-failover")) // se va aqui
				.build()

				;
	}
	
	@Bean
	@Profile("noeureka")
	public RouteLocator configLocalNoEureka(RouteLocatorBuilder builder) {
		log.debug("en NO EUREKA");
		System.out.println("***************NO EUREKA********************");
		System.out.println("perfil NO eureka->" + perfil);
		return builder.routes().route(ruta -> ruta.path("/yoda/*")// cuando se ponga esto en el path
				.uri("http://localhost:8087")) // se va aqui
				.route(ruta -> ruta.path("/superhero/*")// cuando se ponga esto en el path
						.uri("http://localhost:8085")) // se va aqui
				.build()
				
				;
	}

}
