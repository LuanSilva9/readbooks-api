package com.readbooks.api.infrastructure.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.readbooks.api.application.exceptions.BusinessException;
import com.readbooks.api.application.exceptions.NotFoundException;
import com.readbooks.api.infrastructure.controllers.dto.response.ErrorDto;

@ControllerAdvice
public class HandlerException {
    @ExceptionHandler({
        BusinessException.class
    })
    public ResponseEntity<ErrorDto> handleBadRequest(RuntimeException exception) {
        return ResponseEntity.badRequest().body(new ErrorDto(exception.getMessage()));
    }


    @ExceptionHandler({
        NotFoundException.class
    })
    public ResponseEntity<ErrorDto> handleNotFound(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(exception.getMessage()));
    }
}
