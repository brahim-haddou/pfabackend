package com.example.pfabackend.exceptions;


public class SpringPfaException extends RuntimeException {
    public SpringPfaException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }
    public SpringPfaException(String exMessage){
        super(exMessage);
    }
}
