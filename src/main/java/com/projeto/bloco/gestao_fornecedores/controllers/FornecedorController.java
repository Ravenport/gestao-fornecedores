package com.projeto.bloco.gestao_fornecedores.controllers;

import com.projeto.bloco.gestao_fornecedores.models.Fornecedor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/controle/fornecedores")
public class FornecedorController {

    @PostMapping
    public ResponseEntity<String> createFornecedor() {
        return ResponseEntity.ok("Fornecedor criado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> readFornecedores() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/{fornecedorId}")
    public ResponseEntity<Fornecedor> readFornecedor(@PathVariable int fornecedorId) {
        return ResponseEntity.ok(new Fornecedor());
    }

    @PutMapping
    public ResponseEntity<String> updateFornecedor() {
        return ResponseEntity.ok("Fornecedor atualizado com sucesso!");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFornecedor() {
        return ResponseEntity.ok("Fornecedor deletado com sucesso!");
    }
}
