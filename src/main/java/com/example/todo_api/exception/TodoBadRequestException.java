package com.example.todo_api.exception;

public class TodoBadRequestException extends RuntimeException {
    public TodoBadRequestException(String message) {
        super(message);
    }

    public TodoBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
