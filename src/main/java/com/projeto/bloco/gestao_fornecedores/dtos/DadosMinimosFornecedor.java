package com.projeto.bloco.gestao_fornecedores.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DadosMinimosFornecedor {
    private Long id;
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
}
