package com.polsl.factoringcompany.exceptions;

import java.util.UUID;

/**
 * The type Id not found in database exception.
 * @author Michal Goral
 * @version 1.0
 */
public class IdNotFoundInDatabaseException extends RuntimeException {
    /**
     * Instantiates a new id not found in database exception with simple message.
     *
     * @param message the message
     */
    public IdNotFoundInDatabaseException(String message) {
        super(message);
    }

    /**
     * Instantiates a new id not found in database exception with string that
     * represents entity, and it's id.
     *
     * @param object the object
     * @param id     the id
     */
    public IdNotFoundInDatabaseException(String object, Long id) {
        super(object + " " + id + " not found in database");
    }

    /**
     * Instantiates a new id not found in database exception with string that
     * represents entity, and it's uuid.
     *
     * @param object the object
     * @param id     the id
     */
    public IdNotFoundInDatabaseException(String object, UUID id) {
        super(object + " " + id + " not found in database");
    }
}
