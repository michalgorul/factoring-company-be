package com.polsl.factoringcompany.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Id not found advice.
 * @author Michal Goral
 * @version 1.0
 */
@ControllerAdvice
public class IdNotFoundAdvice {

    /**
     * Id not found handler.
     *
     * @param ex the IdNotFoundInDatabaseException
     * @return the string
     */
    @ResponseBody
    @ExceptionHandler(IdNotFoundInDatabaseException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String idNotFoundHandler(IdNotFoundInDatabaseException ex) {
        return ex.getMessage();
    }
}
