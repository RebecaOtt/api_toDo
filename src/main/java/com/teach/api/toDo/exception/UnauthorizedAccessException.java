package com.teach.api.toDo.exception;

public class UnauthorizedAccessException extends RuntimeException{
    public UnauthorizedAccessException(String message) { super(message); }
}
