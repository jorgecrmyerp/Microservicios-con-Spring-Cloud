package com.jgr.basico.kafka.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Class AoBasicoKafkaCloudStream.
 * incluimos web,actuator,spring for apache kafka,cloud stream y spring for apache kafka streams
 * http://localhost:8080/swagger-ui/index.html#/
 * documento el metodo buscaCharactersGameOfThronespara que se vea su descripcion larga en swagger
 * en el properties indico que quiero que solo salga uno de los dos, el controller2 no va a salir
 * 
 * 
 */
@SpringBootApplication
public class AoBasicoKafkaCloudStream {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AoBasicoKafkaCloudStream.class, args);
	}

}
