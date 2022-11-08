package com.jgr.micro.alumnos.cache.redis.models.service;

import java.util.Optional;

import com.jgr.micro.alumnos.cache.redis.models.Alumno;

/**
 * The Interface IAlumnoService.
 */
public interface IAlumnoService {

	/**
	 * Find all.
	 *
	 * @return the iterable
	 */
	public Iterable<Alumno> findAll();

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	public Optional<Alumno> findById(Long id);

	/**
	 * Save.
	 *
	 * @param al the al
	 * @return the alumno
	 */
	public Alumno save(Alumno al);

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	public void delete(Long id);

	/**
	 * Delete.
	 *
	 * @param al the al
	 */
	public void delete(Alumno al);

	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the optional
	 */
	public Optional<Alumno> findByEmail(String email);

	/**
	 * Por email.
	 *
	 * @param email the email
	 * @return the optional
	 */
	public Optional<Alumno> porEmail(String email);

	/**
	 * Exist by email.
	 *
	 * @param email the email
	 * @return true, if successful
	 */
	public boolean existsByEmail(String email);

	/**
	 * Find all alumos by id list. Le pasamos una lista de id de alumnos y los
	 * devuelve en un iterable
	 * 
	 * @param ids the ids
	 * @return the iterable
	 */
	public Iterable<Alumno> findAllById(Iterable<Long> ids);

}
