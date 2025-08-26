package com.projeto.bloco.gestao_fornecedores.repositories;

import com.projeto.bloco.gestao_fornecedores.models.Endereco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, Integer> {
}
