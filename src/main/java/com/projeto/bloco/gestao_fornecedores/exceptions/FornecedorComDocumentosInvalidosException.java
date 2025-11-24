package com.projeto.bloco.gestao_fornecedores.exceptions;

public class FornecedorComDocumentosInvalidosException extends RuntimeException {
    public FornecedorComDocumentosInvalidosException(String message) {
        super(message);
    }

    public FornecedorComDocumentosInvalidosException(String message, Throwable cause) {
        super(message, cause);
    }
}
