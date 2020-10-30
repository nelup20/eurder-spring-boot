package com.neluplatonov.eurder.exception;

public class NoCustomerFoundException extends RuntimeException{
    public NoCustomerFoundException(String message) {
        super(message);
    }
}
