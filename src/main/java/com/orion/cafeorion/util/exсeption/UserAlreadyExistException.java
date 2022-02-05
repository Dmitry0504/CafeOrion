package com.orion.cafeorion.util.exсeption;

/**
 * @author Dmitriy
 * @since 06.02.2022
 */
public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String username) {
        super(String.format("User with username '%s' is already exist!", username));
    }
}
