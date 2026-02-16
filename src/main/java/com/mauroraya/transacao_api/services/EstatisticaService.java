package com.mauroraya.transacao_api.services;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mauroraya.transacao_api.models.Estatistica;
import com.mauroraya.transacao_api.models.Transacao;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

@Service
public class EstatisticaService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MeterRegistry registry;
    private final TransacaoService transacaoService;

    public EstatisticaService(
        MeterRegistry registry,
        TransacaoService transacaoService
    ) {
        this.registry = registry;
        this.transacaoService = transacaoService;
    }

    Estatistica calcularEstatisticas(int intervalo) {
        logger.info("Calculando estatísticas para intervalo de {} segundos", intervalo);

        List<Transacao> transacoes = transacaoService.obterRecentes(intervalo);

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

    public Estatistica obterEstatisticas(int intervalo) {
        Timer timer = registry.timer("estatistica.calculo");
        return timer.record(() -> calcularEstatisticas(intervalo));
    }
}
