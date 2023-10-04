package com.tpool.restproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

    // StudentNotFound exceptions
    @ExceptionHandler
    public ResponseEntity<StudentErrorReponse> handleException(StudentNotFoundException studentNotFoundException){
        StudentErrorReponse studentErrorReponse = new StudentErrorReponse(
                404,
                studentNotFoundException.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(studentErrorReponse, HttpStatus.NOT_FOUND);
    }

    // Generic exceptions
    @ExceptionHandler
    public ResponseEntity<StudentErrorReponse> genericException(Exception exception){
        StudentErrorReponse studentErrorReponse = new StudentErrorReponse(
                HttpStatus.I_AM_A_TEAPOT.value(),
                exception.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(studentErrorReponse, HttpStatus.I_AM_A_TEAPOT);
    }

}
