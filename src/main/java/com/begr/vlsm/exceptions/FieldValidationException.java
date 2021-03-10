package com.begr.vlsm.exceptions;

import java.util.List;

import org.springframework.validation.FieldError;

public class FieldValidationException extends RuntimeException {

    private static final long serialVersionUID = 3156743245678932L;

    public FieldValidationException(List<FieldError> fieldErrors) {
        super(fieldErrorParse(fieldErrors));
    }

    private static String fieldErrorParse(List<FieldError> fieldErrors){
        String message = null;
        for (FieldError fieldError : fieldErrors) {
            if (message != null){
                message = message + "\n" + fieldError.getField()+ ": " + fieldError.getDefaultMessage();
            }else{
                message = fieldError.getField()+ ": " + fieldError.getDefaultMessage();
            }
            
        }
        return message;
    }
}
