package com.readbooks.api.application.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Entidade não encontrada.");
    }

    public NotFoundException(String entityName) {
        super("Entidade não encontrada: " + entityName);
    }
}
