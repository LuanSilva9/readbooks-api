package com.readbooks.api.application.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException() {
        super("Erro - Essa ação viola a regra de negocio do sistema");
    }

    public BusinessException(String message) {
        super("Erro - Essa ação viola a regra de negocio do sistema:" + message);
    }
}
