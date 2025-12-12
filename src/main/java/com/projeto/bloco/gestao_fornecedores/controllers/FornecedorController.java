package com.projeto.bloco.gestao_fornecedores.controllers;

import com.projeto.bloco.gestao_fornecedores.dtos.DadosAtualizacaoFornecedorDTO;
import com.projeto.bloco.gestao_fornecedores.dtos.DadosFornecedorDTO;
import com.projeto.bloco.gestao_fornecedores.exceptions.FornecedorInvalidoException;
import com.projeto.bloco.gestao_fornecedores.exceptions.FornecedorNaoEncontradoException;
import com.projeto.bloco.gestao_fornecedores.models.Fornecedor;
import com.projeto.bloco.gestao_fornecedores.models.enums.FornecedorStatus;
import com.projeto.bloco.gestao_fornecedores.services.FornecedorService;
import com.projeto.bloco.gestao_fornecedores.vos.FornecedorVO;
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
    public ResponseEntity<String> criarFornecedor(@RequestBody Fornecedor fornecedor) throws FornecedorInvalidoException {
        fornecedorService.criarFornecedor(fornecedor);
        return ResponseEntity.ok("Fornecedor criado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<DadosFornecedorDTO>> buscarTodosFornecedores() {
        List<DadosFornecedorDTO> fornecedores = fornecedorService.buscarTodosFornecedores();
        return ResponseEntity.ok(fornecedores);
    }

    @GetMapping("/buscar/cnpj/{cnpj}")
    public ResponseEntity<FornecedorVO> buscarFornecedorPorCnpj(@PathVariable String cnpj) throws FornecedorNaoEncontradoException {
        try {
            Fornecedor dados = fornecedorService.buscarFornecedorCnpj(cnpj);

            FornecedorVO fornecedor = new FornecedorVO(
                    dados.getNomeFantasia(),
                    dados.getRazaoSocial(),
                    dados.getProdutoFornecido(),
                    dados.getCnpj(),
                    dados.getTelefone(),
                    dados.getEmail(),
                    dados.getStatus()
            );
            return ResponseEntity.ok(fornecedor);
        } catch (Exception e) {
            throw new FornecedorNaoEncontradoException("Fornecedor nao encontrado!", e);
        }
    }

    @GetMapping("/buscar/id/{idFornecedor}")
    public ResponseEntity<FornecedorVO> buscarFornecedorPorId(@PathVariable Long idFornecedor) throws FornecedorNaoEncontradoException {
        try {
            Fornecedor dados = fornecedorService.buscarFornecedorId(idFornecedor);

            FornecedorVO fornecedor = new FornecedorVO(
                    dados.getNomeFantasia(),
                    dados.getRazaoSocial(),
                    dados.getProdutoFornecido(),
                    dados.getCnpj(),
                    dados.getTelefone(),
                    dados.getEmail(),
                    dados.getStatus()
            );
            return ResponseEntity.ok(fornecedor);
        } catch (Exception e) {
            throw new FornecedorNaoEncontradoException("Fornecedor nao encontrado!", e);
        }
    }

    @PutMapping
    public ResponseEntity<FornecedorVO> atualizarFornecedor(@RequestBody DadosAtualizacaoFornecedorDTO dadosAtualizados) throws FornecedorInvalidoException {
        Fornecedor dados = fornecedorService.atualizarFornecedor(dadosAtualizados);
        FornecedorVO fornecedor = new FornecedorVO(
                dados.getNomeFantasia(),
                dados.getRazaoSocial(),
                dados.getProdutoFornecido(),
                dados.getCnpj(),
                dados.getTelefone(),
                dados.getEmail(),
                dados.getStatus()
        );

        return ResponseEntity.ok(fornecedor);
    }

    @DeleteMapping("/{cnpj}")
    public ResponseEntity<String> desativarFornecedor(@PathVariable String cnpj) throws FornecedorInvalidoException {
        fornecedorService.atualizarStatusFornecedorPorCnpj(cnpj, FornecedorStatus.DESATIVADO);
        return ResponseEntity.ok("Fornecedor desativado com sucesso!");
    }
}
