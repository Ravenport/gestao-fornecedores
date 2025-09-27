package com.projeto.bloco.gestao_fornecedores.controllers;

import com.projeto.bloco.gestao_fornecedores.models.Fornecedor;
import com.projeto.bloco.gestao_fornecedores.services.FornecedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fornecedores")
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
    public ResponseEntity<List<Fornecedor>> readFornecedores() {
        List<Fornecedor> fornecedores = fornecedorService.getAll();
        return ResponseEntity.ok(fornecedores);
    }

    @GetMapping("/{fornecedorId}")
    public ResponseEntity<Fornecedor> readFornecedor(@PathVariable long fornecedorId) {
        Fornecedor fornecedor = fornecedorService.get(fornecedorId);
        return ResponseEntity.ok(fornecedor);
    }

    @PutMapping
    public ResponseEntity<Fornecedor> updateFornecedor(@RequestBody Fornecedor fornecedor) {
        Fornecedor fornecedorAtualizado = fornecedorService.update(fornecedor);
        return ResponseEntity.ok(fornecedorAtualizado);
    }

    @DeleteMapping("/{fornecedorId}")
    public ResponseEntity<String> deleteFornecedor(@PathVariable long fornecedorId) {
        fornecedorService.delete(fornecedorId);
        return ResponseEntity.ok("Fornecedor deletado com sucesso!");
    }
}
