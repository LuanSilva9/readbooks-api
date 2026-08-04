package com.readbooks.api.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.readbooks.api.errors.exception.BusinessException;
import com.readbooks.api.errors.http.ErrorResponse;

@ControllerAdvice
public class HandleExceptions {
    @ExceptionHandler({
        BusinessException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequest(RuntimeException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponse(exception.getMessage()));
    }
}
