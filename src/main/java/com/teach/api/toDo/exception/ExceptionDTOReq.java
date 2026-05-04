package com.teach.api.toDo.exception;

import org.springframework.http.HttpStatus;

public record ExceptionDTOReq(HttpStatus status, String message) {
}
