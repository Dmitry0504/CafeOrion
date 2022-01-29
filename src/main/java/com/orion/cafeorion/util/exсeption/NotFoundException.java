package com.orion.cafeorion.util.exсeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Dmitriy
 * @since 29.01.2022
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(String entityName, int id) {
        super(String.format("%s with id = %d not found!", entityName, id));
    }
}
