package com.example.demo.ui.model.response;

/**
 * Created by asus-pc on 4/29/2021.
 */
public enum  ErrorMessages {
    MISSING_REQUIRED_FIELD("a required field is missing"),
    RECORD_ALREADY_EXIST("a recored with this field already exist"),
    INTERNAL_SERVER_ERROR("Internal erver error"),
    NO_RECORD_FOUND("record with provided id not found"),
    AUTHENTICATION_FAILED("authentication failed"),
    COULD_NOT_UPDATE_RECORD("could not update record"),
    COULD_NOT_DELETE_RECORD("could not delete record"),
    EMAIL_ADDRESS_NOT_VERIFIED("Email address could not be verified");

    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
