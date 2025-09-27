package com.projeto.bloco.gestao_fornecedores.repositories;

import com.projeto.bloco.gestao_fornecedores.models.HistoricoDeMudancas;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoMudancasRepository extends CrudRepository<HistoricoDeMudancas, Long> {
}
