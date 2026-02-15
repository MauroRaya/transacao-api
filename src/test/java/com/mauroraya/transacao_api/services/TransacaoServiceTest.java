package com.mauroraya.transacao_api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mauroraya.transacao_api.interfaces.GenericRepository;
import com.mauroraya.transacao_api.models.Transacao;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {
    @Mock
    private GenericRepository<Transacao> transacaoRepository;

    @InjectMocks
    private TransacaoService transacaoService;

    @Test
    public void deveRetornarTransacoesQuandoDataHoraDentroIntervalo() {
        List<Transacao> transacoes = List.of(
            new Transacao(0, OffsetDateTime.now()),
            new Transacao(0, OffsetDateTime.now().minusSeconds(61))
        );

        when(transacaoRepository.obter())
            .thenReturn(transacoes);

        List<Transacao> recentes = transacaoService.obterRecentes(60);
        assertEquals(1, recentes.size());
    }
}
