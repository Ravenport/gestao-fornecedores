package com.projeto.bloco.gestao_fornecedores.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.bloco.gestao_fornecedores.models.enums.FornecedorStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "fornecedores")
@Data
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeFantasia;

    @Column(nullable = false)
    private String razaoSocial;

    @Column(nullable = false)
    private String produtoFornecido;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String cnpj;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt_BR")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime dataCadastro;

    @Column(nullable = false)
    private FornecedorStatus status;
}
