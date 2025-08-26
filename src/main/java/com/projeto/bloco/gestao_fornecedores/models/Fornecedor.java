package com.projeto.bloco.gestao_fornecedores.models;

import jakarta.persistence.*;

@Entity
@Table(name = "fornecedores")
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String produtoFornecido;

    @Column(nullable = false)
    private String Telefone;

    @Column(nullable = false)
    private String Email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="endereco_id", nullable = false)
    private Endereco endereco;

    @Column(nullable = false)
    private boolean status;
}
