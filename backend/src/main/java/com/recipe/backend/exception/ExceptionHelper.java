package com.recipe.backend.exception;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ExceptionHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHelper.class);

	@ExceptionHandler(value = { NoSuchElementException.class })
	public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex) {
		LOGGER.error(ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		LOGGER.error(ex.getMessage());
		return new ResponseEntity<>("Please provide integer value greater than zero",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = { EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
		LOGGER.error(ex.getMessage());
		return new ResponseEntity<>("Recipe does not exists",HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(TransactionSystemException.class)
	public ResponseEntity<Object> handleConstrainException(TransactionSystemException ex){
		LOGGER.error(ex.getMessage());
		ArrayList<String> errors = new ArrayList<>();
	    if (ex.getRootCause() instanceof ConstraintViolationException) {
	        ConstraintViolationException constraintViolationException = (ConstraintViolationException) ex.getRootCause();
	        if(constraintViolationException!=null) {
	        	Set<ConstraintViolation<?>> constraintViolations=constraintViolationException.getConstraintViolations();
			    for(ConstraintViolation<?> constraints : constraintViolations) {
			    	errors.add(constraints.getMessage());
			    }
	        }
		    
	    }
	    return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}
}
