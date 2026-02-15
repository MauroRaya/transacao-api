package com.mauroraya.transacao_api.services;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mauroraya.transacao_api.models.Estatistica;
import com.mauroraya.transacao_api.models.Transacao;

@Service
public class EstatisticaService {
    private final TransacaoService transacaoService;

    public EstatisticaService(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    public Estatistica obterEstatisticas() {
        List<Transacao> transacoes = transacaoService.obterRecentes();

        DoubleSummaryStatistics stats = transacoes
            .stream()
            .collect(Collectors.summarizingDouble(
                transacao -> transacao.valor()
            ));

        return new Estatistica(
            stats.getCount(),
            stats.getSum(),
            stats.getAverage(),
            stats.getCount() == 0 ? 0 : stats.getMin(),
            stats.getCount() == 0 ? 0 : stats.getMax()
        );
    }
}
