package com.example.pfabackend.exceptions;

public class ProfesseurNotFoundException  extends RuntimeException{
    public ProfesseurNotFoundException(String message) {
        super(message);
    }
}
