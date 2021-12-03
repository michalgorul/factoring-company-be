package com.polsl.factoringcompany.exceptions;

/**
 * The type Value improper exception.
 * @author Michal Goral
 * @version 1.0
 */
public class ValueImproperException extends IllegalArgumentException {
    /**
     * Instantiates a new Value improper exception.
     *
     * @param name the message
     */
    public ValueImproperException(String name) {
        super("The name '" + name + "' is not appropriate");
    }

    /**
     * Instantiates a new Value improper exception.
     *
     * @param value  the value
     * @param object the object
     */
    public ValueImproperException(String value, String object) {
        super("The " + object + " '" + value + "' is not appropriate");
    }
}
