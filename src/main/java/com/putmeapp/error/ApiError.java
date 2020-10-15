package com.putmeapp.error;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiError {
    private HttpStatus status;
    private int statusVal;
    private String message;
    private List<String> errors;
    private Exception exception;

    public ApiError() {
        super();
    }

    public ApiError(final HttpStatus status, final int statusVal, final String message, final List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.statusVal = statusVal;
    }

    public ApiError(final HttpStatus status, final int statusVal, final String message, final String error) {
        super();
        this.status = status;
        this.statusVal = statusVal;
        this.message = message;
        this.errors = Arrays.asList(error);
    }

    public ApiError(final HttpStatus status, final int statusVal, final String message, final List<String> errors,
            final Exception exception) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.statusVal = statusVal;
        this.exception = exception;
    }

    public ApiError(final HttpStatus status, final int statusVal, final String message, final String error,
            final Exception exception) {
        super();
        this.status = status;
        this.statusVal = statusVal;
        this.message = message;
        this.errors = Arrays.asList(error);
        this.exception = exception;
    }

}
