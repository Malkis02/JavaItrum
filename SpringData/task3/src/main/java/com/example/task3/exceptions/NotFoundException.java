package com.example.task3.exceptions;

public class NotFoundException extends RuntimeException{
    private final String message;

    public NotFoundException(String message) {
        this.message = message;
    }
}
