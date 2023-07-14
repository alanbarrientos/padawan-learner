package com.completejavacourse.advanced.exceptions;

public class ZeroDivisorException extends Exception {

    public ZeroDivisorException(Exception exception) {
        super(exception);
    }

    public ZeroDivisorException(String message) {
        super(message);
    }
}
