package com.projeto.bloco.gestao_fornecedores.exceptions;

import java.io.InvalidClassException;

public class FornecedorInvalidoException extends InvalidClassException {
    public FornecedorInvalidoException(String message) {
        super(message);
    }

    public FornecedorInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }
}
