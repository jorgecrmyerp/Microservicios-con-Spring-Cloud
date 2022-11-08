package com.jgr.micro.cursos.cache.client.rest.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.jgr.micro.cursos.cache.models.entity.Alumno;

/**
 * Relacion con el microservicio Alumnos.
 */
@FeignClient(name="servicio-alumnos",url="localhost:8001")//hardcode relacion microservicio con su puerto
public interface AlumnoFeign {
	
	/**
	 * Detalle alumno a partir de un id.
	 *
	 * @param id the id
	 * @return the alumno
	 */
	@GetMapping("/{id}")
	public Alumno detalleAlumno(@PathVariable Long id);
	
	
	/**
	 * Alta alumno.
	 *
	 * @param al the al
	 * @return the alumno
	 */
	@PostMapping("/")
	public Alumno altaAlumno(@RequestBody Alumno al);
	
	
	/**
	 * Detalle alumnos pasando una lista de ids.
	 *
	 * @param ids lista de the ids de alumnos
	 * @return the iterable
	 */
	@GetMapping("/alumnos-por-curso")
	public Iterable<Alumno> alumnosCursoRequestParam(@RequestParam Iterable<Long> ids);

}
