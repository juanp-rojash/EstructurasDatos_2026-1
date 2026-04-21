package com.example.demo.Model.Excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){

        Map<String, String> errorMapper = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {

            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            errorMapper.put(field, message);

        });

        return errorMapper;
    }

}
