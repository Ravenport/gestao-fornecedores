package com.projeto.bloco.gestao_fornecedores.repositories;

import com.projeto.bloco.gestao_fornecedores.models.HistoricoMudancasCampos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoMudancasCamposRepository extends CrudRepository<HistoricoMudancasCampos, Long> {
}
