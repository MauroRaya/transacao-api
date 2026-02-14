package com.mauroraya.transacao_api.services;

import org.springframework.stereotype.Service;

import com.mauroraya.transacao_api.models.Transacao;
import com.mauroraya.transacao_api.interfaces.GenericRepository;

@Service
public class TransacaoService {
    private final GenericRepository<Transacao> transacaoRepository;

    public TransacaoService(GenericRepository<Transacao> transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public void criar(Transacao transacao) {
        transacaoRepository.criar(transacao);
    }
}
