package com.projeto.bloco.gestao_fornecedores.services;

import com.projeto.bloco.gestao_fornecedores.models.Documentos;
import com.projeto.bloco.gestao_fornecedores.models.HistoricoMudancasCampos;
import com.projeto.bloco.gestao_fornecedores.repositories.DocumentosRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentosService {
    private final DocumentosRepository documentosRepository;
    private final HistoricoMudancaService historicoMudancaService;

    public DocumentosService(DocumentosRepository documentosRepository, HistoricoMudancaService historicoMudancaService) {
        this.documentosRepository = documentosRepository;
        this.historicoMudancaService = historicoMudancaService;
    }

    public Documentos update(Documentos documentosAtualizado) {
        Documentos documentos = documentosRepository.findById(documentosAtualizado.getId()).get();
        List<HistoricoMudancasCampos> camposAlterados = new ArrayList<>();

        camposAlterados.add(new HistoricoMudancasCampos("nome", documentos.getNome()));
        documentos.setNome(documentosAtualizado.getNome());

        camposAlterados.add(new HistoricoMudancasCampos("descricao", documentos.getDescricao()));
        documentos.setDescricao(documentosAtualizado.getDescricao());

        camposAlterados.add(new HistoricoMudancasCampos("mimeType", documentos.getMimeType()));
        documentos.setMimeType(documentosAtualizado.getMimeType());

        camposAlterados.add(new HistoricoMudancasCampos("caminho", documentos.getCaminho()));
        documentos.setCaminho(documentosAtualizado.getCaminho());

        historicoMudancaService.registrarMudanca("documentos_fornecedores", camposAlterados);

        return documentosRepository.save(documentos);
    }

    public Documentos get(long id) {
        return documentosRepository.findById(id).get();
    }

    public List<Documentos> getAll() {
        return (List<Documentos>) documentosRepository.findAll();
    }

    public void delete(long id) {
        documentosRepository.deleteById(id);
    }

    public void create(Documentos documentos) {
        documentosRepository.save(documentos);
    }
}
