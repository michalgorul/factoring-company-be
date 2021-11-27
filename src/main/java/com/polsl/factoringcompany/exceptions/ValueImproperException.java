package com.polsl.factoringcompany.exceptions;

public class ValueImproperException extends IllegalArgumentException {
    public ValueImproperException(String name) {
        super("The name '" + name + "' is not appropriate");
    }

    public ValueImproperException(String value, String object) {
        super("The " + object + " '" + value + "' is not appropriate");
    }
}
