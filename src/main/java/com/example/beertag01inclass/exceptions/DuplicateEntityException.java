package com.example.beertag01inclass.exceptions;

public class DuplicateEntityException extends RuntimeException{
    public DuplicateEntityException() {
        super();
    }

    public DuplicateEntityException(String message) {
        super(message);
    }
}
