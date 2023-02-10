package com.a608.modac.controller;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EntityControllerAdvice {
	@ExceptionHandler({NoSuchElementException.class})
	public ResponseEntity<String> NoEntityException(final NoSuchElementException e) {
		System.out.println(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
	}
}