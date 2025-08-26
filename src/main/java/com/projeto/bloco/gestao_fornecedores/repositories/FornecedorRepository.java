package com.projeto.bloco.gestao_fornecedores.repositories;

import com.projeto.bloco.gestao_fornecedores.models.Fornecedor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends CrudRepository<Fornecedor, Integer> {
}
