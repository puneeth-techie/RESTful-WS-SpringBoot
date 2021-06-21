package com.rest.ws.exception;

public class UserServiceException extends RuntimeException {
    /**
     * Calling RuntimeException by passing argument to the super constructor.
     * @param errorMessage
     */
    public UserServiceException(String errorMessage){
        super(errorMessage);
    }
}
