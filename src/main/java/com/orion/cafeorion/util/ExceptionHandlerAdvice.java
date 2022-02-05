package com.orion.cafeorion.util;

import com.orion.cafeorion.util.exсeption.NotFoundException;
import com.orion.cafeorion.util.exсeption.Response;
import com.orion.cafeorion.util.exсeption.UserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Dmitriy
 * @since 29.01.2022
 */
@ControllerAdvice(basePackages = "com.orion.cafeorion")
public class ExceptionHandlerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Response> handleUserAlreadyExistException(UserAlreadyExistException e) {
        return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrorList = e.getBindingResult().getFieldErrors();
        Map<String, String> errorMessage = new HashMap<>();
        for(FieldError f: fieldErrorList) {
            errorMessage.put(f.getField(), f.getDefaultMessage());
        }
        return new ResponseEntity<>(new Response(errorMessage.toString()), HttpStatus.BAD_REQUEST);
    }
}
