package com.jgr.micro.cursos.cache.service;

import java.util.Optional;

import com.jgr.micro.cursos.cache.models.entity.Alumno;
import com.jgr.micro.cursos.cache.models.entity.Curso;

// TODO: Auto-generated Javadoc
/**
 * The Interface ICursoService.
 */
public interface ICursoService {
	
	/**
	 * Find all.
	 *
	 * @return the iterable
	 */
	public Iterable<Curso> findAll();
	
	/**
	 * Find all sorted by nombre desc.
	 *
	 * @return the iterable
	 */
	public Iterable <Curso> findAllSortedByNombreDesc();
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	public Optional<Curso> findById(Long id);
	
	/**
	 * Save.
	 *
	 * @param curso the curso
	 * @return the curso
	 */
	public Curso save(Curso curso);
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	public void delete(Long id);
	
	/**
	 * Asignar alumno a un curso.
	 * El alumno ya existe
	 *
	 * @param alumno the alumno
	 * @param cursoid the cursoid
	 * @return the optional
	 */
	public Optional<Alumno> asignarAlumnoCurso(Alumno alumno,Long cursoid);
	
	
	/**
	 * Alta alumno.
	 * Damos de alta un alumno y lo asignamos a un curso
	 *
	 * @param alumno the alumno
	 * @param cursoid the cursoid
	 * @return the optional
	 */
	public Optional<Alumno> altaAlumnoCurso(Alumno alumno,Long cursoid);
	
	/**
	 * Eliminar relacion alumno curso.
	 *
	 * @param alumno the alumno
	 * @param cursoid the cursoid
	 * @return the optional
	 */
	public Optional<Alumno> eliminarRelacionAlumnoCurso(Alumno alumno,Long cursoid);
	
	/**
	 * Le pasamos el id de un curso y nos devuelve el curso con los alumnos relacionados.
	 *
	 * @param cursoId the curso id
	 * @return the optional
	 */
	public Optional <Curso> alumnosCursoporIdCurso(Long cursoId);
	
	
	 /**
 	 * Eliminar curso usuario por id.
 	 *
 	 * @param id the id
 	 */
 	public void eliminarCursoUsuarioPorId(Long id);
 	
 	
 	/**
	  * Limpiar cache.
	  */
	 public void limpiarCache();
}
