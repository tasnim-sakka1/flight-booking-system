package com.iit.flightbooking.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyGlobalExceptionHandler {

//    Catches MethodArgumentNotValidException (triggered when @Valid fails on a request).
//    Builds a Map<String,String> to collect validation messages per field.
//    Iterates through BindingResult errors, casts to FieldError, and stores field -> message.
//    Returns HTTP 400 Bad Request with that map as the JSON response body.
//    BindingResult: It contains errors (both FieldError and ObjectError),
//    rejected values, and messages created by the DataBinder
//    when binding request params/JSON to your object.
//    In a controller, put it immediately after the validated argument:

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> myMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> response = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(err -> {
            String fieldName = ((FieldError) err).getField();
            String message = err.getDefaultMessage();
            response.put(fieldName, message);
        });
        return new ResponseEntity<Map<String, String>>(response,
                HttpStatus.BAD_REQUEST);
    }
}
