package com.projeto.bloco.gestao_fornecedores;

import com.projeto.bloco.gestao_fornecedores.models.enums.FornecedorStatus;
import com.projeto.bloco.gestao_fornecedores.models.events.GestaoDocumentoDataEvent;
import com.projeto.bloco.gestao_fornecedores.services.FornecedorService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class KafkaListeners {
    private FornecedorService fornecedorService;

    public KafkaListeners(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @KafkaListener(topics = "gestaoDocumentos", groupId = "fornecedores")
    void listener(String data) {
        GestaoDocumentoDataEvent dadosMapeados = mapearDados(data);

        if(dadosMapeados.getStatusDocumento() != "ATIVO") {
            fornecedorService.alterarStatus(dadosMapeados.getIdFornecedor(), FornecedorStatus.DOCUMENTOS_INVALIDOS);
        }
    }

    private GestaoDocumentoDataEvent mapearDados(String data) {
        String[] dados = data.split(",");
        HashMap<String, String> dadosMapeados = new HashMap<>();

        for(String dado : dados) {
            String[] tempArray = dado.split(":");
            dadosMapeados.put(tempArray[0], tempArray[1]);
        }

        GestaoDocumentoDataEvent gestaoDocumentoDataEvent = new GestaoDocumentoDataEvent(
                Long.parseLong(dadosMapeados.get("idDocumento")),
                Long.parseLong(dadosMapeados.get("idFornecedor")),
                dadosMapeados.get("nome"),
                dadosMapeados.get("status")
        );

        return gestaoDocumentoDataEvent;
    }
}
