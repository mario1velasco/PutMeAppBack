package com.putmeapp.error;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.extern.java.Log;

@Data
@Log
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
        this.errors = this.errorMessageInfo(errors, exception);
        this.statusVal = statusVal;
    }

    public ApiError(final HttpStatus status, final int statusVal, final String message, final String error) {
        super();
        this.status = status;
        this.statusVal = statusVal;
        this.message = message;
        this.errors = this.errorMessageInfo(Arrays.asList(error), exception);
    }

    public ApiError(final HttpStatus status, final int statusVal, final String message, final List<String> errors,
            final Exception exception) {
        super();
        this.status = status;
        this.message = message;
        this.errors = this.errorMessageInfo(errors, exception);
        this.statusVal = statusVal;
        this.exception = exception;
    }

    public ApiError(final HttpStatus status, final int statusVal, final String message, final String error,
            final Exception exception) {
        super();
        this.status = status;
        this.statusVal = statusVal;
        this.message = message;
        this.errors = this.errorMessageInfo(Arrays.asList(error), exception);
        this.exception = exception;
    }

    private List<String> errorMessageInfo(final List<String> errors, final Exception exception) {
        log.info("Log Working -- errorMessageInfo");
        List<String> errorsResult = this.findErrorMessagesInException(exception);
        errorsResult.addAll(errors);
        return errorsResult;
    }

    private List<String> findErrorMessagesInException(final Throwable throwable) {
        List<String> errorsResult = new ArrayList<>();
        if (throwable != null && throwable.getMessage() != null) {
            errorsResult.add(throwable.getMessage());
        }
        if (throwable != null && throwable.getCause() != null) {
            errorsResult.addAll(this.findErrorMessagesInException(throwable.getCause()));
        }
        return errorsResult;
    }

}
