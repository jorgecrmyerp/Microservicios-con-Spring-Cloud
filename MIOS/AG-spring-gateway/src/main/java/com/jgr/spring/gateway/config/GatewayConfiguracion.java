package com.jgr.spring.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguracion {

	@Bean
	public RouteLocator configLocalNoEureka(RouteLocatorBuilder builder) {
		return builder.routes().build();
	}

}
