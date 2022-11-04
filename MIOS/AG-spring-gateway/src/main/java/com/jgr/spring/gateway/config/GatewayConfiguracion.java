package com.jgr.spring.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguracion {

	@Bean
	public RouteLocator configLocalNoEureka(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(ruta->ruta.path("/yoda/*")//cuando se ponga esto en el path
						.uri("http://localhost:8087")) //se va aqui
				.route(ruta->ruta.path("/superhero/*")//cuando se ponga esto en el path
						.uri("http://localhost:8085")) //se va aqui
				.build()
				
				
				;
	}

}
