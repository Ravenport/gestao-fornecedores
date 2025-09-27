package com.projeto.bloco.gestao_fornecedores.services;

import com.projeto.bloco.gestao_fornecedores.models.Fornecedor;
import com.projeto.bloco.gestao_fornecedores.models.HistoricoMudancasCampos;
import com.projeto.bloco.gestao_fornecedores.repositories.FornecedorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FornecedorService {
    private final FornecedorRepository fornecedorRepository;
    private final HistoricoMudancaService historicoMudancaService;

    public FornecedorService(FornecedorRepository fornecedorRepository, HistoricoMudancaService historicoMudancaService) {
        this.fornecedorRepository = fornecedorRepository;
        this.historicoMudancaService = historicoMudancaService;
    }

    public Fornecedor update(Fornecedor fornecedorAtualizado) {
        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorAtualizado.getId()).get();
        List<HistoricoMudancasCampos> camposAlterados = new ArrayList<>();

        camposAlterados.add(new HistoricoMudancasCampos("nome", fornecedor.getNome()));
        fornecedor.setNome(fornecedorAtualizado.getNome());

        camposAlterados.add(new HistoricoMudancasCampos("email", fornecedor.getEmail()));
        fornecedor.setEmail(fornecedorAtualizado.getEmail());

        camposAlterados.add(new HistoricoMudancasCampos("active", fornecedor.isActive()? "true" : "false"));
        fornecedor.setActive(fornecedorAtualizado.isActive());

        camposAlterados.add(new HistoricoMudancasCampos("telefone", fornecedor.getTelefone()));
        fornecedor.setTelefone(fornecedorAtualizado.getTelefone());

        camposAlterados.add(new HistoricoMudancasCampos("endereco", fornecedor.getEndereco().toString()));
        fornecedor.setEndereco(fornecedorAtualizado.getEndereco());

        historicoMudancaService.registrarMudanca("fornecedores", camposAlterados);

        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor get(long id) {
        return fornecedorRepository.findById(id).get();
    }

    public List<Fornecedor> getAll() {
        return (List<Fornecedor>) fornecedorRepository.findAll();
    }

    public void delete(long id) {
        fornecedorRepository.deleteById(id);
    }

    public void create(Fornecedor fornecedor) {
        fornecedorRepository.save(fornecedor);
    }
}
