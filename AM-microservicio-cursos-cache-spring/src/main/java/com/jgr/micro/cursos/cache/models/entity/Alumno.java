package com.jgr.micro.cursos.cache.models.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Instantiates a new alumno.
 * Este NO se guarda en la tabla, la necesitamos para tratar el tipo alumno, pero el que va a guardarse
 * es el del microservicio alumnos
 */


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Alumno {

    /** The id. */
   
    private Long id;

    /** The nombre. */    
	
    private String nombre;

    /** The email. */
	
    private String email;

    /** The password. */	
	
    private String password;
    
    /** The create at. */
    
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
    
	/**
	 * Pre persist.
	 */
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return Objects.equals(this.id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	
	

}