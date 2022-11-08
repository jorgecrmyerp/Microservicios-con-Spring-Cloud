package com.jgr.micro.alumnos.cache.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jgr.micro.alumnos.cache.models.Alumno;
import com.jgr.micro.alumnos.cache.models.service.IAlumnoService;

/**
 * The Class AlumnoController.
 */
@RestController
public class AlumnoController {

	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(AlumnoController.class);

	/** The i alumno service. */
	@Autowired
	private IAlumnoService iAlumnoService;

	/**
	 * Obtener alumno por id path variable.
	 *
	 * @return the response entity
	 */
	@GetMapping
	public ResponseEntity<?> listarAlumnos() {

		return ResponseEntity.ok(iAlumnoService.findAll());

	}

	/**
	 * Obtener alumno por id path variable.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerAlumnoPorIdPathVariable(@PathVariable Long id) {

		logger.debug("***************en pathvariable*****************");
		Optional<Alumno> al = iAlumnoService.findById(id);

		if (al.isPresent()) {
			return ResponseEntity.ok(iAlumnoService.findById(id));
		}
		return ResponseEntity.notFound().build();

	}

	/**
	 * Obtener alumno por id request param.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/request-param/")
	public ResponseEntity<?> obtenerAlumnoPorIdRequestParam(@RequestParam Long id) {

		logger.debug("******en REQUESTPARAM**************");
		Optional<Alumno> al = iAlumnoService.findById(id);

		if (al.isPresent()) {
			return ResponseEntity.ok(iAlumnoService.findById(id));
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Actualiza alumno.
	 *
	 * @param al     the al
	 * @param result the result
	 * @param id     the id
	 * @return the response entity
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizaAlumno(@Valid @RequestBody Alumno al, BindingResult result,
			@PathVariable Long id) {

		if (result.hasErrors()) {
			return validar(result);
		}

		Optional<Alumno> o = iAlumnoService.findById(id);

		if (!o.isPresent()) {
			logger.debug("Microservicio Alumno->actualizaAlumno");
			return ResponseEntity.notFound().build();
		}

		Alumno alDb = o.get();

		if (!al.getEmail().isEmpty() && !al.getEmail().equalsIgnoreCase(alDb.getEmail())
				&& iAlumnoService.porEmail(al.getEmail()).isPresent()) {
			return ResponseEntity.badRequest()
					.body(Collections.singletonMap("mensaje", "Ya existe un alumno con ese email"));

		}

		alDb.setNombre(al.getNombre());
		alDb.setEmail(al.getEmail());
		alDb.setPassword(al.getPassword());
		return ResponseEntity.status(HttpStatus.CREATED).body(iAlumnoService.save(alDb));

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
	 * Alta alumno.
	 *
	 * @param al     the al
	 * @param result the result
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<?> altaAlumno(@Valid @RequestBody Alumno al, BindingResult result) {

		System.out.println("en alta alumnos" + al.toString());

		if (result.hasErrors()) {
			return validar(result);
		}

		if (!al.getEmail().isEmpty() && iAlumnoService.findByEmail(al.getEmail()).isPresent()) {
			return ResponseEntity.badRequest()
					.body(Collections.singletonMap("mensaje", "Ya existe un alumno con ese email"));

		}

		Alumno alDb = new Alumno();
		alDb.setNombre(al.getNombre());
		alDb.setEmail(al.getEmail());
		alDb.setPassword(al.getPassword());

		return ResponseEntity.status(HttpStatus.CREATED).body(iAlumnoService.save(alDb));

	}

	/**
	 * Borra alumno id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> borraAlumnoId(@PathVariable Long id) {

		Optional<Alumno> o = iAlumnoService.findById(id);

		if (!o.isPresent()) {
			logger.debug("Microservicio Alumno->actualizaAlumno");

			return ResponseEntity.notFound().build();
		}
		iAlumnoService.delete(id);

		return ResponseEntity.noContent().build();

	}

	/**
	 * Borra alumno alumno.
	 *
	 * @param al the al
	 * @return the response entity
	 */
	@DeleteMapping("/borra-alumno")
	public ResponseEntity<?> borraAlumnoAlumno(@Valid @RequestBody Alumno al) {

		Optional<Alumno> o = iAlumnoService.findById(al.getId());

		if (!o.isPresent()) {
			logger.debug("Microservicio Alumno->actualizaAlumno");

			return ResponseEntity.notFound().build();
		}
		iAlumnoService.delete(al);
		return ResponseEntity.noContent().build();

	}

	/**
	 * Alumnos curso request param. a partir de una lista de long con los id de
	 * alumnos devuelve el detalle de los alumnos
	 * 
	 * @param ids the ids
	 * @return the response entity
	 */
	@GetMapping("/alumnos-por-curso")
	public ResponseEntity<?> alumnosCursoRequestParam(@RequestParam List<Long> ids) {

		logger.debug("alumnoz-AlumnosCursoREquest->" + ids.get(0));
		List<Alumno> lista = new ArrayList<>();

		lista.forEach(System.out::println);

		return ResponseEntity.ok(iAlumnoService.findAllById(ids));

	}

}
