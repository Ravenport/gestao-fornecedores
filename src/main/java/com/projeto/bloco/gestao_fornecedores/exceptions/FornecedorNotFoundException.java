package com.projeto.bloco.gestao_fornecedores.exceptions;

public class FornecedorNotFoundException extends RuntimeException {
    public FornecedorNotFoundException(String message) {
        super(message);
    }

    public FornecedorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
