package com.projeto.bloco.gestao_fornecedores.services;

import com.projeto.bloco.gestao_fornecedores.dtos.DadosMinimosFornecedor;
import com.projeto.bloco.gestao_fornecedores.models.Fornecedor;
import com.projeto.bloco.gestao_fornecedores.models.enums.FornecedorStatus;
import com.projeto.bloco.gestao_fornecedores.repositories.FornecedorRepository;
import com.projeto.bloco.gestao_fornecedores.dtos.DocumentoResponseVO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FornecedorService {
    private final FornecedorRepository fornecedorRepository;
    private final RestTemplate restTemplate;

    public FornecedorService(FornecedorRepository fornecedorRepository, RestTemplate restTemplate) {
        this.fornecedorRepository = fornecedorRepository;
        this.restTemplate = restTemplate;
    }

    public Fornecedor update(Fornecedor fornecedorAtualizado) {
        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorAtualizado.getId()).get();

        fornecedor.setNomeFantasia(fornecedorAtualizado.getNomeFantasia());
        fornecedor.setRazaoSocial(fornecedorAtualizado.getRazaoSocial());
        fornecedor.setCnpj(fornecedorAtualizado.getCnpj());
        fornecedor.setEmail(fornecedorAtualizado.getEmail());
        fornecedor.setStatus(fornecedorAtualizado.getStatus());
        fornecedor.setTelefone(fornecedorAtualizado.getTelefone());

        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor get(long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id).get();

        FornecedorStatus status = validarFornecedor(fornecedor);

        fornecedor.setStatus(status);
        fornecedorRepository.save(fornecedor);

        return fornecedor;
    }

    public List<DadosMinimosFornecedor> getAll() {
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();
        List<DadosMinimosFornecedor> response = new ArrayList<>();

        for(Fornecedor fornecedor : fornecedores) {
            response.add(new DadosMinimosFornecedor(fornecedor.getId(), fornecedor.getCnpj(), fornecedor.getRazaoSocial(), fornecedor.getNomeFantasia()));
        }

        return response;
    }

    public void alterarStatus(long id, FornecedorStatus status) {
        Fornecedor fornecedor = get(id);
        fornecedor.setStatus(status);
        fornecedorRepository.save(fornecedor);
    }

    public void create(Fornecedor fornecedor) {
        FornecedorStatus status = validarFornecedor(fornecedor);
        fornecedor.setStatus(status);

        fornecedorRepository.save(fornecedor);
    }

    public FornecedorStatus validarFornecedor(Fornecedor fornecedor) {
        if(fornecedor.getId() == null)
            return FornecedorStatus.AGUARDANDO_DOCUMENTACAO;

        ParameterizedTypeReference<List<DocumentoResponseVO>> typeReference = new ParameterizedTypeReference<>() {};
        List<DocumentoResponseVO> response = restTemplate.exchange(
                "http://localhost:8080/api/documentos/buscar/todos/portador/{idPortador}",
                HttpMethod.GET,
                null,
                typeReference,
                fornecedor.getId()
        ).getBody();

        if(response == null || response.isEmpty()) {
            return  FornecedorStatus.AGUARDANDO_DOCUMENTACAO;
        }

        FornecedorStatus fornecedorStatus = FornecedorStatus.ATIVO;
        for (DocumentoResponseVO documentoResponseVO : response) {
            if (!documentoResponseVO.getStatus().equals("ATIVO")) {
                fornecedorStatus = FornecedorStatus.DOCUMENTOS_INVALIDOS;
                break;
            }
        }
        return fornecedorStatus;
    }
}
