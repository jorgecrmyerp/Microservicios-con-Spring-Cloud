package com.jgr.micro.alumnos.cache.redis.models.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jgr.micro.alumnos.cache.redis.client.rest.feign.CursoClienteFeign;
import com.jgr.micro.alumnos.cache.redis.models.Alumno;
import com.jgr.micro.alumnos.cache.redis.models.repository.IAlumnoRepository;

/**
 * The Class AlumnoServiceImpl.
 */
@Service
public class AlumnoServiceImpl implements IAlumnoService {

	/** The alumno repository. */
	@Autowired
	private IAlumnoRepository alumnoRepository;

	/** The curso cliente feign. */
	@Autowired
	private CursoClienteFeign cursoClienteFeign;

	/**
	 * Find all.
	 *
	 * @return the iterable
	 */
	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findAll() {
		return alumnoRepository.findAll();
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<Alumno> findById(Long id) {
		return alumnoRepository.findById(id);
	}

	/**
	 * Save.
	 *
	 * @param al the al
	 * @return the alumno
	 */
	@Override
	@Transactional
	public Alumno save(Alumno al) {

		return alumnoRepository.save(al);

	}

	/**
	 * Delete por id. borra alumno y da de baja la relacion curso_alumno en el
	 * microservicio cursos
	 *
	 * @param id the id
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		alumnoRepository.deleteById(id);
		cursoClienteFeign.eliminarCursoAlumnoId(id);

	}

	/**
	 * Delete por alumno. borra alumno y da de baja la relacion curso_alumno en el
	 * microservicio cursos
	 * 
	 * @param al the al
	 */
	@Override
	@Transactional
	public void delete(Alumno al) {
		alumnoRepository.delete(al);
		cursoClienteFeign.eliminarCursoAlumnoId(al.getId());

	}

	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the optional
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<Alumno> findByEmail(String email) {
		return alumnoRepository.findByEmail(email);
	}

	/**
	 * Por email.
	 *
	 * @param email the email
	 * @return the optional
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<Alumno> porEmail(String email) {

		return alumnoRepository.porEmail(email);
	}

	/**
	 * Exists by email.
	 *
	 * @param email the email
	 * @return true, if successful
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean existsByEmail(String email) {

		return alumnoRepository.existsByEmail(email);
	}

	/**
	 * Find all Alumnos by id.
	 *
	 * @param ids the ids
	 * @return the iterable
	 */
	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findAllById(Iterable<Long> ids) {
		return alumnoRepository.findAllById(ids);
	}

}
