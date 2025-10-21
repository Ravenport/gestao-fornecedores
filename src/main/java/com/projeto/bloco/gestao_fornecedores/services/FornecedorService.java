package com.projeto.bloco.gestao_fornecedores.services;

import com.projeto.bloco.gestao_fornecedores.models.Fornecedor;
import com.projeto.bloco.gestao_fornecedores.repositories.FornecedorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FornecedorService {
    private final FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    public Fornecedor update(Fornecedor fornecedorAtualizado) {
        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorAtualizado.getId()).get();

        fornecedor.setNome(fornecedorAtualizado.getNome());
        fornecedor.setEmail(fornecedorAtualizado.getEmail());
        fornecedor.setStatus(fornecedorAtualizado.getStatus());
        fornecedor.setTelefone(fornecedorAtualizado.getTelefone());

        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor get(long id) {
        return fornecedorRepository.findById(id).get();
    }

    public List<Fornecedor> getAll() {
        return (List<Fornecedor>) fornecedorRepository.findAll();
    }

    public void alterarStatus(long id, String status) {
        Fornecedor fornecedor = get(id);
        fornecedor.setStatus(status);
        fornecedorRepository.save(fornecedor);
    }

    public void create(Fornecedor fornecedor) {
        fornecedorRepository.save(fornecedor);
    }
}
