package com.polsl.factoringcompany.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Name improper advice.
 * @author Michal Goral
 * @version 1.0
 */
@ControllerAdvice
public class NameImproperAdvice {

    /**
     * Id not found handler.
     *
     * @param ex the ValueImproperException
     * @return the string
     */
    @ResponseBody
    @ExceptionHandler(ValueImproperException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String idNotFoundHandler(ValueImproperException ex) {
        return ex.getMessage();
    }
}
