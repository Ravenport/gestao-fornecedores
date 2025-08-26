package com.projeto.bloco.gestao_fornecedores.services;

import com.projeto.bloco.gestao_fornecedores.repositories.FornecedorRepository;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService {
    private FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {}
}
