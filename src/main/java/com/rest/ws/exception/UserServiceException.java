package com.rest.ws.exception;

import com.rest.ws.ui.model.response.ErrorMessages;

public class UserServiceException extends RuntimeException {
    /**
     * Calling RuntimeException by passing argument to the super constructor.
     * @param errorMessage
     */
    public UserServiceException(ErrorMessages errorMessage){
        super(String.valueOf(errorMessage));
    }
}
