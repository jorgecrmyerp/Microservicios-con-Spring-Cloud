package com.jgr.micro.cursos.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// TODO: Auto-generated Javadoc
/**
 * The Class AmMicroServicioCursosCacheApplication.
 *  * para probar el tratamiento del cache
 *  probar con asignarAlumnoCurso/altaAlumnoCurso del microservicio cursos
 *  *  http://localhost:8002/asignar-alumno/
 *  http://localhost:8002/alta-alumno
 *  en estos metodos le he puesto un sleep para que tarde
 *  
 */
@SpringBootApplication
@EnableFeignClients
@EnableWebMvc//para que funcione swagger
public class AmMicroServicioCursosCacheApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AmMicroServicioCursosCacheApplication.class, args);
	}

}
