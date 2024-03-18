package com.APIclubApp.clubApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AssociatedCategoriesException extends RuntimeException{
    public AssociatedCategoriesException(String message) {
        super(message);
    }

}