package com.polsl.factoringcompany.exceptions;

public class NotUniqueException extends RuntimeException {

    public NotUniqueException(String itemTypeName, String paramName, String paramValue) {
        super(itemTypeName + " (" + paramName + ": " + paramValue + ") exists in database");
    }
}
