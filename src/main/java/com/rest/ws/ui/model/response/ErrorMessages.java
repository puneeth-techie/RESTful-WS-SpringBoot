package com.rest.ws.ui.model.response;

public enum ErrorMessages {
    MISSING_REQUIRED_FIELD("Missing required fields. Please provide all the fields."),
    RECORD_ALREADY_EXISTS("Record already exists."),
    EMAIL_ADDRESS_NOT_VERIFIED("Email address could not be verified"),
    NO_USER_ID_FOUND("Records with the given user id not found.");

    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
}
