
package com.jgr.micro.alumnos.cache.models.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jgr.micro.alumnos.cache.models.Alumno;


// TODO: Auto-generated Javadoc
/**
 * The Interface IAlumnosRepository.
 */

public interface IAlumnoRepository extends CrudRepository<Alumno, Long> {
	
	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the optional
	 */
	public Optional <Alumno> findByEmail(String email);
	
	/**
	 * Por email.
	 *
	 * @param email the email
	 * @return the optional
	 */
	@Query("select al from Alumno al where al.email=?1")
	public Optional <Alumno> porEmail(String email);
	
	
	/**
	 * Exist by email.
	 *
	 * @param email the email
	 * @return true, if successful
	 */
	public boolean existsByEmail(String email);
	

}
