package com.projeto.bloco.gestao_fornecedores.exceptions;

public class FornecedorNaoEncontradoException extends RuntimeException {
    public FornecedorNaoEncontradoException(String message) {
        super(message);
    }

    public FornecedorNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}
