package com.projeto.bloco.gestao_fornecedores.controllers;

import com.projeto.bloco.gestao_fornecedores.models.Fornecedor;
import com.projeto.bloco.gestao_fornecedores.services.FornecedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/validacao/fornecedor")
public class AuditFornecedorController {
    private final FornecedorService fornecedorService;

    public AuditFornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @GetMapping("/contrato/{id}")
    public ResponseEntity<String> validarContratoFornecedor(@PathVariable long id) {


        return ResponseEntity.ok("");
    }

    @GetMapping("/contrato/{id}")
    public ResponseEntity<String> validarDocumentosPendentes(@PathVariable long id) {
        Fornecedor fornecedor = fornecedorService.get(id);

        return ResponseEntity.ok("");
    }
}
