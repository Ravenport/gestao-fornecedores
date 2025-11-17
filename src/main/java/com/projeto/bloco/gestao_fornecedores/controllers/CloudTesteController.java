package com.projeto.bloco.gestao_fornecedores.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teste/cloud")
public class CloudTesteController {

    @Value("${message: Default Hello}")
    private String message;

    @GetMapping("/message")
    public ResponseEntity<String> testeCloud() {
        return ResponseEntity.ok(this.message);
    }
}
