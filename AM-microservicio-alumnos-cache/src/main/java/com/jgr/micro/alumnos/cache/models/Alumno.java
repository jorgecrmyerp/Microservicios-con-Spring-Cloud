package com.jgr.micro.alumnos.cache.models;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class Alumno.
 */
@Entity
@Table(name = "alumnos")

/**
 * Instantiates a new alumno.
 */
@NoArgsConstructor

/**
 * Instantiates a new alumno.
 *
 * @param id       the id
 * @param nombre   the nombre
 * @param email    the email
 * @param password the password
 * @param createAt the create at
 */
@AllArgsConstructor

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data
public class Alumno {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The nombre. */
	@NotBlank // no admite blancos en el nombre,y no puede estar vacio
	private String nombre;

	/** The email. */
	@NotEmpty(message = "Email erroneo")
	@Column(unique = true)
	@Email
	private String email;

	/** The password. */
	@NotBlank
	private String password;

	/** The create at. */
	@Column(name = "create_at")
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
