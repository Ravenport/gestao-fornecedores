package com.projeto.bloco.gestao_fornecedores.controllers;

import com.projeto.bloco.gestao_fornecedores.dtos.DadosMinimosFornecedor;
import com.projeto.bloco.gestao_fornecedores.exceptions.FornecedorNotFoundException;
import com.projeto.bloco.gestao_fornecedores.models.Fornecedor;
import com.projeto.bloco.gestao_fornecedores.models.enums.FornecedorStatus;
import com.projeto.bloco.gestao_fornecedores.services.FornecedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {
    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @PostMapping
    public ResponseEntity<String> createFornecedor(@RequestBody Fornecedor fornecedor) {
        fornecedorService.create(fornecedor);
        return ResponseEntity.ok("Fornecedor criado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<DadosMinimosFornecedor>> readFornecedores() {
        List<DadosMinimosFornecedor> fornecedores = fornecedorService.getAll();
        return ResponseEntity.ok(fornecedores);
    }

    @GetMapping("/{fornecedorId}")
    public ResponseEntity<Fornecedor> readFornecedor(@PathVariable long fornecedorId) {
        try {
            Fornecedor fornecedor = fornecedorService.get(fornecedorId);
            return ResponseEntity.ok(fornecedor);
        } catch (Exception e) {
            System.out.println("Default Locale: " + Locale.getDefault());
            throw new FornecedorNotFoundException("Fornecedor nao encontrado!", e);
        }
    }

    @PutMapping
    public ResponseEntity<Fornecedor> updateFornecedor(@RequestBody Fornecedor fornecedor) {
        Fornecedor fornecedorAtualizado = fornecedorService.update(fornecedor);
        return ResponseEntity.ok(fornecedorAtualizado);
    }

    @DeleteMapping("/{fornecedorId}")
    public ResponseEntity<String> deleteFornecedor(@PathVariable long fornecedorId) {
        fornecedorService.alterarStatus(fornecedorId, FornecedorStatus.DESATIVADO);
        return ResponseEntity.ok("Fornecedor desativado com sucesso!");
    }
}
