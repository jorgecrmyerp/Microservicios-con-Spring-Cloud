package com.jgr.basico.kafka.springcloud.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


// TODO: Auto-generated Javadoc
/**
 * The Class CharacterNotFound.
 * Controla errores,por lo general mejor si ya existen
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No encontrado")
public class CharacterNotFound extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9114869430189169813L;
	
	

}
