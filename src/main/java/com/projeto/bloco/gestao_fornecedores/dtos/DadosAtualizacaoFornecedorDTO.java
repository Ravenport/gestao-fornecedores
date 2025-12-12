package com.projeto.bloco.gestao_fornecedores.dtos;

import lombok.Data;
import lombok.Getter;

@Data
public class DadosAtualizacaoFornecedorDTO {
    private Long idFornecedor;
    private String email;
    private String status;
    private String telefone;
}
