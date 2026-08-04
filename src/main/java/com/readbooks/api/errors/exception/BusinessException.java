package com.readbooks.api.errors.exception;

public class BusinessException extends RuntimeException {
    public BusinessException() {
        super("Erro - Essa ação viola a regra de negocio do sistema");
    }

    public BusinessException(String message) {
        super("Erro - Essa ação viola a regra de negocio do sistema:" + message);
    }
}
