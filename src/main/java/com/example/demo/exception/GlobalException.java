package com.example.demo.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.Response.GlobalResponse;

@RestControllerAdvice
public class GlobalException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponse> handleValidationExceptions( MethodArgumentNotValidException ex) {
        System.out.println(ex.getBindingResult());
      GlobalResponse response = new GlobalResponse();
      response.setStatus(false);
      response.setMessage(ex.getBindingResult().getFieldError().getDefaultMessage());
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
