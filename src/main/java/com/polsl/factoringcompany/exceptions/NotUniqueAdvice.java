package com.polsl.factoringcompany.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Not unique advice.
 * @author Michal Goral
 * @version 1.0
 */
@ControllerAdvice
public class NotUniqueAdvice {

    /**
     * Not unique entity found handler.
     *
     * @param ex the ex
     * @return the string
     */
    @ResponseBody
    @ExceptionHandler(NotUniqueException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String notUniqueHandler(NotUniqueException ex) {
        return ex.getMessage();
    }
}
