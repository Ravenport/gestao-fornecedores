package com.projeto.bloco.gestao_fornecedores.services;

import com.projeto.bloco.gestao_fornecedores.dtos.DadosAtualizacaoFornecedorDTO;
import com.projeto.bloco.gestao_fornecedores.dtos.DadosFornecedorDTO;
import com.projeto.bloco.gestao_fornecedores.exceptions.FornecedorInvalidoException;
import com.projeto.bloco.gestao_fornecedores.exceptions.FornecedorNaoEncontradoException;
import com.projeto.bloco.gestao_fornecedores.models.Fornecedor;
import com.projeto.bloco.gestao_fornecedores.models.enums.FornecedorStatus;
import com.projeto.bloco.gestao_fornecedores.repositories.FornecedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {
    private final FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    public void criarFornecedor(Fornecedor fornecedor) throws FornecedorInvalidoException {
        try {
            fornecedor.setStatus(FornecedorStatus.AGUARDANDO_DOCUMENTACAO);
            fornecedorRepository.save(fornecedor);
        } catch (Exception e) {
            throw new FornecedorInvalidoException(e.getMessage());
        }
    }

    public Fornecedor atualizarFornecedor(DadosAtualizacaoFornecedorDTO dadosAtualizados) throws FornecedorInvalidoException {
        try {
            Fornecedor fornecedor = fornecedorRepository.findById(dadosAtualizados.getIdFornecedor()).get();

            fornecedor.setEmail(dadosAtualizados.getEmail());
            fornecedor.setStatus(FornecedorStatus.valueOf(dadosAtualizados.getStatus()));
            fornecedor.setTelefone(dadosAtualizados.getTelefone());
            
            return fornecedorRepository.save(fornecedor);
        } catch (Exception e) {
            throw new FornecedorInvalidoException(e.getMessage());
        }
    }

    public void atualizarStatusFornecedorPorCnpj(String cnpj, FornecedorStatus status) throws FornecedorInvalidoException {
        try {
            Fornecedor fornecedor = buscarFornecedorCnpj(cnpj);
            fornecedor.setStatus(status);
            fornecedorRepository.save(fornecedor);
        } catch (Exception e) {
            throw new FornecedorInvalidoException(e.getMessage());
        }
    }

    public void atualizarStatusFornecedorPorId(Long idFornecedor, FornecedorStatus status) throws FornecedorInvalidoException {
        try {
            Fornecedor fornecedor = buscarFornecedorId(idFornecedor);
            fornecedor.setStatus(status);
            fornecedorRepository.save(fornecedor);
        } catch (Exception e) {
            throw new FornecedorInvalidoException(e.getMessage());
        }
    }

    public Fornecedor buscarFornecedorCnpj(String cnpj) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findByCnpj(cnpj);

        if(fornecedor.isPresent())
            return fornecedor.get();
        else
            throw new FornecedorNaoEncontradoException("Fornecedor não Encontrado, tente novamente!");
    }

    public Fornecedor buscarFornecedorId(Long idFornecedor) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(idFornecedor);

        if(fornecedor.isPresent())
            return fornecedor.get();
        else
            throw new FornecedorNaoEncontradoException("Fornecedor não Encontrado, tente novamente!");
    }

    public List<DadosFornecedorDTO> buscarTodosFornecedores() {
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();
        List<DadosFornecedorDTO> response = new ArrayList<>();

        for(Fornecedor fornecedor : fornecedores) {
            response.add(new DadosFornecedorDTO(fornecedor.getId(), fornecedor.getCnpj(), fornecedor.getRazaoSocial(), fornecedor.getNomeFantasia()));
        }

        return response;
    }
}
