package com.dswiss.api.ddrive.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandlerController {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Error exception (MethodArgumentNotValidException ex, WebRequest request) {
		return Error.builder()
				.code(HttpStatus.BAD_REQUEST.value())
				.message(ex.getAllErrors().get(0).getDefaultMessage())
				.build();
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	public Error exception (ResourceNotFoundException ex, WebRequest request) {
		return Error.builder()
				.code(HttpStatus.NOT_FOUND.value())
				.message(ex.getMessage())
				.build();
	}
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(CryptoException.class)
	public Error exception (CryptoException ex, WebRequest request) {
		return Error.builder()
				.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.message(ex.getMessage())
				.build();
	}
}
