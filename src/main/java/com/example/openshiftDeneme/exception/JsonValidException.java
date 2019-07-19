/*package com.example.openshift_deneme.exception;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.web.context.request.WebRequest;

import com.example.openshift_deneme.json.JsonSchemaValidationException;




@ControllerAdvice
public class JsonValidException {
  @ExceptionHandler(JsonSchemaValidationException.class)
  public final ResponseEntity<ErrorDetails> handleUserNotFoundException(JsonSchemaValidationException ex, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }



}*/