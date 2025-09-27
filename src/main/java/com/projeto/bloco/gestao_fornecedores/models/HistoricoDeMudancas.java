package com.projeto.bloco.gestao_fornecedores.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "historico_mudancas")
@Data
public class HistoricoDeMudancas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String tabelaAlterada;
    private LocalDateTime dataEdicao;

    @OneToMany(mappedBy = "mudanca", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoricoMudancasCampos> camposAlterados;
}
