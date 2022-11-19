package com.example.demo.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppException {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value=Exception.class)
	public ResponseEntity<Object> ExceptionHandler(Exception ex,WebRequest request)
	{
		MessageError error = new MessageError(ex.getMessage(),new Date());
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
