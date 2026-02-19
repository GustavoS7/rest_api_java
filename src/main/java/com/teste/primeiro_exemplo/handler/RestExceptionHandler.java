package com.teste.primeiro_exemplo.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.teste.primeiro_exemplo.model.error.ErrorMessage;
import com.teste.primeiro_exemplo.model.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> handleResourceNotFoundException(
    ResourceNotFoundException exception
  ) {
    ErrorMessage errorMessage = new ErrorMessage(
      "Not Found",
      HttpStatus.NOT_FOUND.value(),
      exception.getMessage()
    );
    return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
  }
}
