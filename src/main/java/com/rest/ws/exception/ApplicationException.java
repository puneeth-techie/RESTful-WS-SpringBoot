package com.rest.ws.exception;

import com.rest.ws.ui.model.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ApplicationException {
    /**
     * Handling the specific exception for UserService
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = {UserServiceException.class})
    public ResponseEntity handlerUserException(UserServiceException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handling all other or General Exceptions
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity handlerAllOtherException(Exception ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
