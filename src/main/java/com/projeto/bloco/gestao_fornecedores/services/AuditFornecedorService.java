package com.projeto.bloco.gestao_fornecedores.services;

import com.projeto.bloco.gestao_fornecedores.models.AuditFornecedor;
import com.projeto.bloco.gestao_fornecedores.models.Fornecedor;
import com.projeto.bloco.gestao_fornecedores.repositories.AuditFornecedorRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public record AuditFornecedorService(AuditFornecedorRepository auditFornecedorRepository, FornecedorService fornecedorService) {
    public String validarContratoFornecedor(long idFornecedor) {
        Fornecedor fornecedor = fornecedorService.get(idFornecedor);

        if(LocalDateTime.now().isAfter(fornecedor.getDataFinalContrato())) {
            fornecedorService.alterarStatus(idFornecedor, "VENCIDO");
            auditFornecedorRepository.save(new AuditFornecedor(fornecedor, "Validacao Contratual", "Contrato vencido. Fornecedor desativado", LocalDateTime.now()));
            return "O contrato do fornecedor, está vencido!";
        }

        if(LocalDateTime.now().isEqual(fornecedor.getDataFinalContrato())) {
            auditFornecedorRepository.save(new AuditFornecedor(fornecedor, "Validacao Contratual", "Contrato vence Hoje", LocalDateTime.now()));
            return "O contrato do fornecedor, vence hoje!";
        }

        auditFornecedorRepository.save(new AuditFornecedor(fornecedor, "Validacao Contratual", "Contrato Valido, vence em: "+ ChronoUnit.DAYS.between(LocalDateTime.now(), fornecedor.getDataFinalContrato()) + " dias", LocalDateTime.now()));
        return "O contrato do fornecedor vence em: " + ChronoUnit.DAYS.between(LocalDateTime.now(), fornecedor.getDataFinalContrato()) + " dias";
    }

    public String validarDocumentosPendentesFornecedor(long idFornecedor) {
        Fornecedor fornecedor = fornecedorService.get(idFornecedor);

        return "";
    }
}
