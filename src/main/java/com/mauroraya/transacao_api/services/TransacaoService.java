package com.mauroraya.transacao_api.services;

import java.time.OffsetDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mauroraya.transacao_api.interfaces.GenericRepository;
import com.mauroraya.transacao_api.models.Transacao;

@Service
public class TransacaoService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final GenericRepository<Transacao> transacaoRepository;

    public TransacaoService(GenericRepository<Transacao> transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public List<Transacao> obterRecentes(int intervalo) {
        OffsetDateTime limite = OffsetDateTime
            .now()
            .minusSeconds(intervalo);

        return this.transacaoRepository
            .obter()
            .stream()
            .filter(transacao -> transacao.dataHora().isAfter(limite))
            .toList();
    }

    public void criar(Transacao transacao) {
        logger.info("Criando transação com o valor {} em {}", 
            transacao.valor(),
            transacao.dataHora()
        );

        transacaoRepository.criar(transacao);
    }

    public void deletarTodos() {
        logger.info("Deletando transações existentes");
        transacaoRepository.deletarTodos();
    }
}
