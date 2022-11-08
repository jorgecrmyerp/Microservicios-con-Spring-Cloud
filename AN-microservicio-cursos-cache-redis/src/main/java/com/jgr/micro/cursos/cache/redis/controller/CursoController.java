package com.jgr.micro.cursos.cache.redis.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jgr.micro.cursos.cache.redis.models.entity.Alumno;
import com.jgr.micro.cursos.cache.redis.models.entity.Curso;
import com.jgr.micro.cursos.cache.redis.service.ICursoService;

import feign.FeignException;

/**
 * The Class CursoController.
 */
@RestController
public class CursoController {

	/** The service. */
	@Autowired
	private ICursoService service;

	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(CursoController.class);

	/**
	 * Listar.
	 *
	 * @return the response entity
	 */
	@GetMapping
	public ResponseEntity<Iterable<Curso>> listar() {

		return ResponseEntity.ok(service.findAll());
	}

	/**
	 * Listar por nombre.
	 *
	 * @return the response entity
	 */
	@GetMapping("/por-nombre/")
	public ResponseEntity<Iterable<Curso>> listarPorNombre() {
		return ResponseEntity.ok(service.findAllSortedByNombreDesc());
	}

	/**
	 * Detalle.
	 *
	 * @param id the id
	 * @return the curso y el detalle de los alumnos relacionados
	 */
	@GetMapping("/{id}")
	
	public ResponseEntity<?> detalle(@PathVariable Long id) {

		// Optional<Curso> o = service.findById(id); //solo saca el curso
		// curso y el detalle de los alumnos relacionados
		Optional<Curso> o = service.alumnosCursoporIdCurso(id);

		if (o.isPresent()) {
			return ResponseEntity.ok(o.get());
		}

		return ResponseEntity.notFound().build();
	}

	/**
	 * Crear.
	 *
	 * @param curso  the curso
	 * @param result the result
	 * @return the response entity
	 */
	@PostMapping("/")
	public ResponseEntity<?> crear(@Valid @RequestBody Curso curso, BindingResult result) {

		if (result.hasErrors()) {
			return validar(result);
		}

		Curso cursoDb = service.save(curso);
		return ResponseEntity.status(HttpStatus.CREATED).body(cursoDb);
	}

	/**
	 * Editar.
	 *
	 * @param curso  the curso
	 * @param result the result
	 * @param id     the id
	 * @return the response entity
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id) {

		if (result.hasErrors()) {
			return validar(result);
		}
		Optional<Curso> o = service.findById(id);
		if (o.isPresent()) {
			Curso cursoDb = o.get();
			cursoDb.setNombre(curso.getNombre());
			return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDb));
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Eliminar.
	 *
	 * @param id     the id
	 * @param result the result
	 * @return the response entity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@Valid @PathVariable Long id, BindingResult result) {

		if (result.hasErrors()) {
			return validar(result);
		}
		Optional<Curso> o = service.findById(id);
		if (o.isPresent()) {
			service.delete(o.get().getId());
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Validar.
	 *
	 * @param result the result
	 * @return the response entity
	 */
	private ResponseEntity<Map<String, String>> validar(BindingResult result) {
		Map<String, String> errores = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
			errores.put("DefaultMessage", err.getDefaultMessage());
			errores.put("Code", err.getCode());
			errores.put("Name", err.getObjectName());

		});
		return ResponseEntity.badRequest().body(errores);
	}

	/**
	 * Asignar alumno que SI existe al curso. en caso de que no exista el curso da
	 * error
	 *
	 * @param alumno  the alumno
	 * @param result  the result
	 * @param cursoId the curso id
	 * @return the response entity
	 */
	@PutMapping("/asignar-alumno/{cursoId}")
	public ResponseEntity<?> asignarAlumnoCurso(@Valid @RequestBody Alumno alumno, BindingResult result,
			@PathVariable Long cursoId) {

		if (result.hasErrors()) {
			return validar(result);
		}

		Optional<Alumno> alumnoAlta = null;

		// si hay error en la comunicacion con feign
		try {
			alumnoAlta = service.asignarAlumnoCurso(alumno, cursoId);
		} catch (FeignException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					Collections.singletonMap("mensaje", "error comunicacion o no existe curso " + e.getMessage()));

		}

		if (alumnoAlta.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(alumnoAlta.get());
		}

		return ResponseEntity.notFound().build();

	}

	/**
	 * Borrar relacion alumno-curso en caso de que no exista el curso da error.
	 *
	 * @param alumno  the alumno
	 * @param result  the result
	 * @param cursoId the curso id
	 * @return the response entity
	 */
	@DeleteMapping("/borrar-alumno/{cursoId}")
	public ResponseEntity<?> eliminarRelacionAlumnoCurso(@RequestBody Alumno alumno, BindingResult result,
			@PathVariable Long cursoId) {

		if (result.hasErrors()) {
			return validar(result);
		}

		Optional<Alumno> alumnoBaja;

		// si hay error en la comunicacion con feign
		try {
			alumnoBaja = service.eliminarRelacionAlumnoCurso(alumno, cursoId);
			System.out.println("borrar alumno id" + alumno.getId() + "-" + cursoId);
			System.out.println("alumnoBaja" + alumnoBaja.get().getId());
		} catch (FeignException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					Collections.singletonMap("mensaje", "error comunicacion o no existe curso " + e.getMessage()));

		}

		if (alumnoBaja.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(alumnoBaja.get());
		}

		return ResponseEntity.notFound().build();

	}

	/**
	 * Alta de nuevo alumno y relacion con el curso.
	 *
	 * @param alumno  the alumno
	 * @param result  the result
	 * @param cursoId the curso id
	 * @return the response entity
	 */
	@PostMapping("/alta-alumno/{cursoId}")
	public ResponseEntity<?> altaAlumnoCurso(@Valid @RequestBody Alumno alumno, BindingResult result,
			@PathVariable Long cursoId) {

		System.out.println("en alta alumo curso" + alumno.toString());

		if (result.hasErrors()) {
			return validar(result);
		}

		Optional<Alumno> alumnoAlta;

		// si hay error en la comunicacion con feign
		try {
			alumnoAlta = service.altaAlumnoCurso(alumno, cursoId);
		} catch (FeignException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("mensaje",
					"error comunicacion o no se pudo crear alumno " + e.getMessage()));

		}

		if (alumnoAlta.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(alumnoAlta.get());
		}

		return ResponseEntity.notFound().build();

	}

	/**
	 * Eliminar curso_alumno por alumnoid.
	 *
	 * @param id     the id
	 * @return the response entity
	 */
	@DeleteMapping("eliminar-curso-alumno/{id}")
	public ResponseEntity<?> eliminarCursoAlumnoId( @PathVariable Long id) {

		Optional<Curso> o = service.findById(id);
		
		if (o.isPresent()) {
			service.eliminarCursoUsuarioPorId(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
		
	}
	
	/**
	 * Limpia cache.
	 *
	 * @return the response entity
	 */
	@DeleteMapping("limpiar-cache")
	public ResponseEntity<?> limpiaCache() {
		service.limpiarCache();
		
		return ResponseEntity.ok("Cache limpio");
		
	}
	

}
