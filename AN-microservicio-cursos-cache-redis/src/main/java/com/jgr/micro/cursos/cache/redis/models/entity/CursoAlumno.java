package com.jgr.micro.cursos.cache.redis.models.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class CursoAlumno.
 */
@Entity
@Table(name = "cursos_alumnos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


public class CursoAlumno implements Serializable{

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The alumno id. */
	@Column(name = "alumno_id", unique = true)
	private Long alumnoId;

	@Override
	public int hashCode() {
		return Objects.hash(alumnoId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CursoAlumno))
			return false;
		CursoAlumno other = (CursoAlumno) obj;
		return Objects.equals(this.alumnoId, other.alumnoId);
	}

	

}
