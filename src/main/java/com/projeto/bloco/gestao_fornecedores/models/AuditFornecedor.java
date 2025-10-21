package com.projeto.bloco.gestao_fornecedores.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Table(name="auditoria_fornecedores")
@Data
public class AuditFornecedor {

    public AuditFornecedor(Fornecedor fornecedor, String nomeAuditoria, String descricaoAuditoria, LocalDateTime dataAuditoria) {
        this.fornecedorAuditado = fornecedor;
        this.nomeAuditoria = nomeAuditoria;
        this.descricaoAuditoria = descricaoAuditoria;
        this.dataAuditoria = dataAuditoria;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_fornecedor")
    private Fornecedor fornecedorAuditado;

    @Column(nullable = false)
    private String nomeAuditoria;

    @Column(nullable = false)
    private String descricaoAuditoria;

    @Column(nullable = false)
    private LocalDateTime dataAuditoria;
}
