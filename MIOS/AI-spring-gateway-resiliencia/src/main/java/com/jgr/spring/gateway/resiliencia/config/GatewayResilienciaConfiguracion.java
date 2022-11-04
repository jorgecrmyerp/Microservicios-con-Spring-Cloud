package com.jgr.spring.gateway.resiliencia.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class GatewayResilienciaConfiguracion {

	
	private static final Logger log = LoggerFactory.getLogger(GatewayResilienciaConfiguracion.class);
	//en el properties local si le pongo noeureka la ruta es hardcode,en otro caso va a eureka
	
	
	@Value("${spring.profiles.active}")
	private String perfil;
	
	
	
	

	
	@Bean
	public RouteLocator configLocalEureka(RouteLocatorBuilder builder) {
		
		return builder.routes().route(ruta -> ruta.path("/yoda/*")// cuando se ponga esto en el path
				.uri("lb://micro-superhero-failover/")) // se va aqui				
				.build()

				;
	}
	
	
	
//	@Bean
//	@Profile("perfilcb")
//	public RouteLocator configResiliencia(RouteLocatorBuilder builder) {
//		log.debug("PERFIL CB");
//		System.out.println("perfil CB");
//		System.out.println("perfil cb->" + perfil);
//		return builder.routes().
//				route(ruta -> ruta.path("/yoda/*")// cuando se ponga esto en el path
//						.filters(f -> {//entra en este filtro
//							f.circuitBreaker(
//									c -> c.setName("perfilcb")//nombre del filtro
//									//.setFallbackUri("forward:/gameof/juegotronos")//se va aqui
//									.setFallbackUri("lb://micro-yoda-resiliencia/gameof/juegotronos")//se va aqui
//									.setRouteId("dbFailover"));//ruta id
//						
//							return f;
//						})
////				.uri("lb://micro-yoda-resiliencia")) // se va aqui si ok
//				.uri("lb://micro-yoda-resiliencia/gameof")) // se va aqui si ok
//				
//				.build()
//				
//				;
//	}

}
