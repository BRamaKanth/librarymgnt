package com.collaberadigital.library.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookNotFoundHandler {

	@ExceptionHandler(value = IllegalArgumentException.class)
	public HeadersBuilder<?> bookNotFound() {
		return ResponseEntity.notFound();
	}
}
