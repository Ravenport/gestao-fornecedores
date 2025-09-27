package com.projeto.bloco.gestao_fornecedores.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "historico_mudancas_campos")
@Data
public class HistoricoMudancasCampos {

    public HistoricoMudancasCampos(String campoAlterado, String valorAnterior) {
        this.campoAlterado = campoAlterado;
        this.valorAnterior = valorAnterior;
    }

    public HistoricoMudancasCampos() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String campoAlterado;
    private String valorAnterior;

    @ManyToOne
    @JoinColumn(name = "mudanca_id")
    private HistoricoDeMudancas mudanca;
}
