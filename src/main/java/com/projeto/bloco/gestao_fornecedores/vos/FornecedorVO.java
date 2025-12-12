package com.projeto.bloco.gestao_fornecedores.vos;

import com.projeto.bloco.gestao_fornecedores.models.enums.FornecedorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FornecedorVO {
    private String nomeFantasia;
    private String razaoSocial;
    private String produtoFornecido;
    private String cnpj;
    private String telefone;
    private String email;
    private FornecedorStatus status;
}
