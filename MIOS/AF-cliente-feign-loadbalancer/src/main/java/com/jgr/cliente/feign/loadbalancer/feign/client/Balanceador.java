package com.jgr.cliente.feign.loadbalancer.feign.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The Class Balanceador.
 * configuracion del balanceador de carga
 */
@Configuration
public class Balanceador {

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(Balanceador.class); 
	
	/**
	 * Discovery client service instance list supplier.
	 *
	 * @param context the context
	 * @return the service instance list supplier
	 */
	@Bean
	public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(
			ConfigurableApplicationContext context) {
		log.info("*****configurando LoadBalancer en Balanceador******");
		return ServiceInstanceListSupplier.
				builder()
				.withBlockingDiscoveryClient()
				.withSameInstancePreference()//preferimos que vaya siempre a la misma maquina
				.build(context);
	}
	
	
	
}
