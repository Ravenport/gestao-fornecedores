package com.projeto.bloco.gestao_fornecedores.models.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GestaoDocumentosEvent {
    private String tipoEvento;
    private Long idDocumento;
    private Long idFornecedor;
    private String nomeDocumento;
    private String statusDocumento;
}
