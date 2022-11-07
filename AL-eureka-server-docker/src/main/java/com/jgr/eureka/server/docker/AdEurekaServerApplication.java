package com.jgr.eureka.server.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AdEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdEurekaServerApplication.class, args);
	}

}
