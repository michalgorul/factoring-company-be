package com.polsl.factoringcompany.exceptions;

/**
 * The type Not unique exception.
 * @author Michal Goral
 * @version 1.0
 */
public class NotUniqueException extends RuntimeException {

    /**
     * Instantiates a new Not unique exception.
     *
     * @param itemTypeName the item type name
     * @param paramName    the param name
     * @param paramValue   the param value
     */
    public NotUniqueException(String itemTypeName, String paramName, String paramValue) {
        super(itemTypeName + " (" + paramName + ": " + paramValue + ") exists in database");
    }
}
