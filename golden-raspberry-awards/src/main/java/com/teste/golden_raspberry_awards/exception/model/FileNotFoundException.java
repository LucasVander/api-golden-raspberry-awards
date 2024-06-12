package com.teste.golden_raspberry_awards.exception.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class FileNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -2034849621628156475L;

	public FileNotFoundException(String message) {
		super(message);
	}
}
