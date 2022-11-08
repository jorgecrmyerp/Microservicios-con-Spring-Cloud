package com.jgr.micro.alumnos.cache.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * The Class AmMicroServicioAlumnosApplication. *
 *  para probar el tratamiento del
 * cache probar con asignarAlumnoCurso/altaAlumnoCurso/buscacurso por id detalle
 * del microservicio cursos * http://localhost:8002/asignar-alumno/
 * http://localhost:8002/alta-alumno localhost:8002/1 creo
 * CursosConfiguracionCache en el microservicio cursos
 * INCLUIR EN EL POM 
 * <!-- https://mvnrepository.com/artifact/org.redisson/redisson -->
<dependency>
    <groupId>org.redisson</groupId>
    <artifactId>redisson</artifactId>
    <version>3.17.7</version>
</dependency>
**TB SE INSTALA CON DOCKER LA IMAGEN DE REDIS
 * 
 * 
 */
@SpringBootApplication
@EnableFeignClients
@EnableWebMvc // para que funcione swagger
public class ANMicroServicioAlumnosApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ANMicroServicioAlumnosApplication.class, args);
	}

}
