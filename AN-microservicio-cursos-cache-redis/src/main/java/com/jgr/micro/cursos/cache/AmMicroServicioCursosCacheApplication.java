package com.jgr.micro.cursos.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * The Class AmMicroServicioCursosCacheApplication.
 *  * para probar el tratamiento del cache
 *  probar con asignarAlumnoCurso/altaAlumnoCurso/buscacurso por id  detalle del microservicio cursos
 *  *  http://localhost:8002/asignar-alumno/
 *  http://localhost:8002/alta-alumno
 *  localhost:8002/1
 *  tambien creo un limpiar cache que NO es obligatorio que sea un endpòint del controlador
 *  creo CursosConfiguracionCache en el microservicio cursos
 *  en estos metodos en la capa de servicio de cursos les he puesto un sleep para que tarde mas
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
