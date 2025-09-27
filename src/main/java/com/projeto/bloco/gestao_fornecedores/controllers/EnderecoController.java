package com.projeto.bloco.gestao_fornecedores.controllers;

import com.projeto.bloco.gestao_fornecedores.models.Endereco;
import com.projeto.bloco.gestao_fornecedores.services.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/endereco")
public class EnderecoController {
    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<String> createEndereco(@RequestBody Endereco endereco) {
        enderecoService.create(endereco);
        return ResponseEntity.ok("Endereco criado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> readEnderecoes() {
        List<Endereco> enderecos = enderecoService.getAll();
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/{enderecoId}")
    public ResponseEntity<Endereco> readEndereco(@PathVariable long enderecoId) {
        Endereco endereco = enderecoService.get(enderecoId);
        return ResponseEntity.ok(endereco);
    }

    @PutMapping
    public ResponseEntity<Endereco> updateEndereco(@RequestBody Endereco endereco) {
        Endereco enderecoAtualizado = enderecoService.update(endereco);
        return ResponseEntity.ok(enderecoAtualizado);
    }

    @DeleteMapping("/{enderecoId}")
    public ResponseEntity<String> deleteEndereco(@PathVariable long enderecoId) {
        enderecoService.delete(enderecoId);
        return ResponseEntity.ok("Endereco deletado com sucesso!");
    }
}
