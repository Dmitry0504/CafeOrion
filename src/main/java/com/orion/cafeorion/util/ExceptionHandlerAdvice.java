package com.orion.cafeorion.util;

import com.orion.cafeorion.util.exсeption.NotFoundException;
import com.orion.cafeorion.util.exсeption.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Dmitriy
 * @since 29.01.2022
 */
@ControllerAdvice(basePackages = "com.orion.cafeorion")
public class ExceptionHandlerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> handleError(NotFoundException e) {
        return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.OK);
    }
}
