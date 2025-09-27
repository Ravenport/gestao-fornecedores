package com.projeto.bloco.gestao_fornecedores.repositories;

import com.projeto.bloco.gestao_fornecedores.models.Documentos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentosRepository extends CrudRepository<Documentos, Long> {
}
