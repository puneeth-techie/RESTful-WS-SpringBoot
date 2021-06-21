package com.rest.ws.exception;

public class UserServiceException extends RuntimeException {
    public UserServiceException(String errorMessage){
        super(errorMessage);
    }
}
