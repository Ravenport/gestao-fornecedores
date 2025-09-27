package com.projeto.bloco.gestao_fornecedores.services;

import com.projeto.bloco.gestao_fornecedores.models.HistoricoDeMudancas;
import com.projeto.bloco.gestao_fornecedores.models.HistoricoMudancasCampos;
import com.projeto.bloco.gestao_fornecedores.repositories.HistoricoMudancasCamposRepository;
import com.projeto.bloco.gestao_fornecedores.repositories.HistoricoMudancasRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoricoMudancaService {
    private final HistoricoMudancasRepository historicoMudancasRepository;
    private final HistoricoMudancasCamposRepository historicoMudancasCamposRepository;

    public HistoricoMudancaService(HistoricoMudancasCamposRepository historicoMudancasCamposRepository, HistoricoMudancasRepository historicoMudancasRepository) {
        this.historicoMudancasCamposRepository = historicoMudancasCamposRepository;
        this.historicoMudancasRepository = historicoMudancasRepository;
    }

    public void registrarMudanca(String tabelaAlterada, List<HistoricoMudancasCampos> camposAlterados) {
        HistoricoDeMudancas historicoDeMudancas = new HistoricoDeMudancas();

        historicoDeMudancas.setTabelaAlterada(tabelaAlterada);
        historicoDeMudancas.setDataEdicao(LocalDateTime.now());

        historicoDeMudancas = historicoMudancasRepository.save(historicoDeMudancas);

        for (HistoricoMudancasCampos campo : camposAlterados) {
            campo.setMudanca(historicoDeMudancas);
            historicoMudancasCamposRepository.save(campo);
        }
    }

    public List<HistoricoDeMudancas> getHistoricoMudancas() {
        return (List<HistoricoDeMudancas>) historicoMudancasRepository.findAll();
    }

    public List<HistoricoMudancasCampos> getHistoricoMudancasCampos() {
        return (List<HistoricoMudancasCampos>) historicoMudancasCamposRepository.findAll();
    }
}
