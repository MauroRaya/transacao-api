package com.mauroraya.transacao_api.services;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mauroraya.transacao_api.interfaces.GenericRepository;
import com.mauroraya.transacao_api.models.Transacao;

@Service
public class TransacaoService {
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
        transacaoRepository.criar(transacao);
    }

    public void deletarTodos() {
        transacaoRepository.deletarTodos();
    }
}
