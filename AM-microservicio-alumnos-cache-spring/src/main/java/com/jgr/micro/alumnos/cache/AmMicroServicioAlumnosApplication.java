package com.jgr.micro.alumnos.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * The Class AmMicroServicioAlumnosApplication. * para probar el tratamiento del
 * cache probar con asignarAlumnoCurso/altaAlumnoCurso/buscacurso por id detalle
 * del microservicio cursos * http://localhost:8002/asignar-alumno/
 * http://localhost:8002/alta-alumno localhost:8002/1 creo
 * CursosConfiguracionCache en el microservicio cursos
 * 
 */
@SpringBootApplication
@EnableFeignClients
@EnableWebMvc // para que funcione swagger
public class AmMicroServicioAlumnosApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AmMicroServicioAlumnosApplication.class, args);
	}

}
