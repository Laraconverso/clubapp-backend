package com.APIclubApp.clubApp.exception;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoChangesException extends RuntimeException {
    public NoChangesException(String message) {
        super(message);
    }
}