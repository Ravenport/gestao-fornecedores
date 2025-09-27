package com.projeto.bloco.gestao_fornecedores.services;

import com.projeto.bloco.gestao_fornecedores.models.Endereco;
import com.projeto.bloco.gestao_fornecedores.models.HistoricoMudancasCampos;
import com.projeto.bloco.gestao_fornecedores.repositories.EnderecoRepository;

import java.util.ArrayList;
import java.util.List;

public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final HistoricoMudancaService historicoMudancaService;

    public EnderecoService(EnderecoRepository enderecoRepository, HistoricoMudancaService historicoMudancaService) {
        this.enderecoRepository = enderecoRepository;
        this.historicoMudancaService = historicoMudancaService;
    }

    public Endereco update(Endereco enderecoAtualizado) {
        Endereco endereco = enderecoRepository.findById(enderecoAtualizado.getId()).get();
        List<HistoricoMudancasCampos> camposAlterados = new ArrayList<>();

        camposAlterados.add(new HistoricoMudancasCampos("cep", endereco.getCep()));
        endereco.setCep(enderecoAtualizado.getCep());

        camposAlterados.add(new HistoricoMudancasCampos("complemento", endereco.getComplemento()));
        endereco.setComplemento(enderecoAtualizado.getComplemento());

        camposAlterados.add(new HistoricoMudancasCampos("numero", endereco.getNumero()));
        endereco.setNumero(enderecoAtualizado.getNumero());

        historicoMudancaService.registrarMudanca("endereco_fornecedor", camposAlterados);

        return enderecoRepository.save(endereco);
    }

    public Endereco get(long id) {
        return enderecoRepository.findById(id).get();
    }

    public List<Endereco> getAll() {
        return (List<Endereco>) enderecoRepository.findAll();
    }

    public void delete(long id) {
        enderecoRepository.deleteById(id);
    }

    public void create(Endereco endereco) {
        enderecoRepository.save(endereco);
    }
}
