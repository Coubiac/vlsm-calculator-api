package com.begr.vlsm.exceptions;

import com.begr.vlsm.model.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Date;


    @ControllerAdvice
    public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {


        @ExceptionHandler(value = {Exception.class, NullPointerException.class, VlsmCalculatorServiceException.class, ArrayIndexOutOfBoundsException.class})
        public ResponseEntity<Object> handleSpecificException(Exception ex, WebRequest request){
            String errorMessageDescription = ex.getLocalizedMessage();
            if(errorMessageDescription == null) {
                errorMessageDescription = ex.toString();
            }


            ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);

            return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

}
