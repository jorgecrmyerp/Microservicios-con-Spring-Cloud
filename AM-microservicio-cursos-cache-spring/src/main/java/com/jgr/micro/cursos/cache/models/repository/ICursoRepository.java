package com.jgr.micro.cursos.cache.models.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jgr.micro.cursos.cache.models.entity.Curso;

/**
 * The Interface ICursoRepository.
 */
public interface ICursoRepository extends  PagingAndSortingRepository<Curso, Long>{
	
    /**
     * Eliminar curso_usuario por id de alumno.
     *
     * @param id the id
     */
    @Modifying
    @Query("delete from CursoAlumno cu where cu.alumnoId=?1")
    public void eliminarCursoUsuarioPorId(Long id);
    
}
