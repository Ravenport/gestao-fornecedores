package com.projeto.bloco.gestao_fornecedores.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
public class DocumentoResponseVO {
    private Long idPortador;
    private String nome;
    private String descricao;
    private String mimeType;
    private String caminho;
    private String status;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt_BR")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataVencimento;
}