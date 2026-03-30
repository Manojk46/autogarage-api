package com.garage.autogarage.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.garage.autogarage.dto.ErrorResponse;

@RestControllerAdvice
public class GobalExceptionHandler {
	@ExceptionHandler(ResourceNotFound.class)
 public ResponseEntity<ErrorResponse> handleResourceNotFound(
		 ResourceNotFound ex){
	 ErrorResponse errorResponse=new ErrorResponse(
			 HttpStatus.NOT_FOUND.value(),
			 ex.getMessage(),
			 LocalDateTime.now());
	 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
 }
	@ExceptionHandler(BadRequest.class)
	public ResponseEntity<ErrorResponse> handleBadRequest(BadRequest ex){
		ErrorResponse errorResponse=new ErrorResponse(
				HttpStatus.BAD_REQUEST.value(),
				ex.getMessage(),
				LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception ex){
		ErrorResponse errorResponse=new ErrorResponse(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				ex.getMessage(),
				LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}
	 @ExceptionHandler(NoResourceFoundException.class)
	    public ResponseEntity<ErrorResponse> handleNoResourceFound(
	            NoResourceFoundException ex) {
	        ErrorResponse error = new ErrorResponse(
	                HttpStatus.NOT_FOUND.value(),
	                ex.getMessage(),
	                LocalDateTime.now()
	        );
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	    }
	
}
