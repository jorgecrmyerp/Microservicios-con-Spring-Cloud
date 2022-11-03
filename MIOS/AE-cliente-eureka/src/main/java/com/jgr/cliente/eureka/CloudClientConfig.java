package com.jgr.cliente.eureka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
@RefreshScope//para que se actualice tb cuando se cambia algo en git
public class CloudClientConfig {
	
	
	@Value("${application.name}")
	private String applicationName;
	
	public String getApplicationName() {
		return applicationName;
	}
	
	
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	

}
