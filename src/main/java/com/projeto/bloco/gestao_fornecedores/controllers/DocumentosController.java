package com.projeto.bloco.gestao_fornecedores.controllers;

import com.projeto.bloco.gestao_fornecedores.models.Documentos;
import com.projeto.bloco.gestao_fornecedores.services.DocumentosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/documentos")
public class DocumentosController {
    private final DocumentosService documentosService;

    public DocumentosController(DocumentosService documentosService) {
        this.documentosService = documentosService;
    }

    @PostMapping
    public ResponseEntity<String> createDocumentos(@RequestBody Documentos documentos) {
        documentosService.create(documentos);
        return ResponseEntity.ok("Documentos criado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<Documentos>> readDocumentoses() {
        List<Documentos> documentos = documentosService.getAll();
        return ResponseEntity.ok(documentos);
    }

    @GetMapping("/{documentosId}")
    public ResponseEntity<Documentos> readDocumentos(@PathVariable long documentosId) {
        Documentos documentos = documentosService.get(documentosId);
        return ResponseEntity.ok(documentos);
    }

    @PutMapping
    public ResponseEntity<Documentos> updateDocumentos(@RequestBody Documentos documentos) {
        Documentos documentosAtualizado = documentosService.update(documentos);
        return ResponseEntity.ok(documentosAtualizado);
    }

    @DeleteMapping("/{documentosId}")
    public ResponseEntity<String> deleteDocumentos(@PathVariable long documentosId) {
        documentosService.delete(documentosId);
        return ResponseEntity.ok("Documentos deletado com sucesso!");
    }
}
