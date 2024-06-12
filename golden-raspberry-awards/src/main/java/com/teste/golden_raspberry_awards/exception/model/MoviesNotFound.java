package com.teste.golden_raspberry_awards.exception.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class MoviesNotFound extends RuntimeException {
	
	private static final long serialVersionUID = 4118188723685451949L;

	public MoviesNotFound(String message) {
		super(message);
	}
}
