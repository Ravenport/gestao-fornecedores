package com.projeto.bloco.gestao_fornecedores.listeners;

import com.projeto.bloco.gestao_fornecedores.exceptions.FornecedorInvalidoException;
import com.projeto.bloco.gestao_fornecedores.models.enums.DocumentosTiposEventos;
import com.projeto.bloco.gestao_fornecedores.models.enums.FornecedorStatus;
import com.projeto.bloco.gestao_fornecedores.models.events.GestaoDocumentosEvent;
import com.projeto.bloco.gestao_fornecedores.services.FornecedorService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class DocumentosListener {
    private final FornecedorService fornecedorService;

    public DocumentosListener(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @KafkaListener(topics = "gestaoDocumentos", groupId = "fornecedores")
    void listener(GestaoDocumentosEvent dados) throws FornecedorInvalidoException {
        if(
            DocumentosTiposEventos.valueOf(dados.getTipoEvento()) == DocumentosTiposEventos.CRIADO
            || DocumentosTiposEventos.valueOf(dados.getTipoEvento()) == DocumentosTiposEventos.ATUALIZADO
        ) {
            if(Objects.equals(dados.getStatusDocumento(), "VENCIDO")) {
                fornecedorService.atualizarStatusFornecedorPorId(dados.getIdFornecedor(), FornecedorStatus.DOCUMENTOS_INVALIDOS);
            } else if(Objects.equals(dados.getStatusDocumento(), "DESATIVADO")) {
                fornecedorService.atualizarStatusFornecedorPorId(dados.getIdFornecedor(), FornecedorStatus.AGUARDANDO_DOCUMENTACAO);
            } else {
                fornecedorService.atualizarStatusFornecedorPorId(dados.getIdFornecedor(), FornecedorStatus.ATIVO);
            }
        } else {
            fornecedorService.atualizarStatusFornecedorPorId(dados.getIdFornecedor(), FornecedorStatus.AGUARDANDO_DOCUMENTACAO);
        }
    }
}
