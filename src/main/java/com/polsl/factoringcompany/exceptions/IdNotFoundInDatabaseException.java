package com.polsl.factoringcompany.exceptions;

import java.util.UUID;

public class IdNotFoundInDatabaseException extends RuntimeException {
    public IdNotFoundInDatabaseException(String message) {
        super(message);
    }

    public IdNotFoundInDatabaseException(String object, Long id) {
        super(object + " " + id + " not found in database");
    }

    public IdNotFoundInDatabaseException(String object, UUID id) {
        super(object + " " + id + " not found in database");
    }
}
