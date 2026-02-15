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

import com.mauroraya.transacao_api.models.Estatistica;
import com.mauroraya.transacao_api.models.Transacao;

@ExtendWith(MockitoExtension.class)
public class EstatisticaServiceTest {
    @Mock
    private TransacaoService transacaoService;

    @InjectMocks
    private EstatisticaService estatisticaService;

    @Test
    public void deveRetornarEstatisticas0seNaoHouverTransacoesRecentes() {
        when(transacaoService.obterRecentes())
            .thenReturn(List.of());

        Estatistica estatisticas = estatisticaService.obterEstatisticas();
        assertEquals(new Estatistica(0, 0, 0, 0, 0), estatisticas);
    }

    @Test
    public void deveRetornarEstatisticasSeHouverTransacoesRecentes() {
        List<Transacao> transacoes = List.of(
            new Transacao(10, OffsetDateTime.now()),
            new Transacao(20, OffsetDateTime.now())
        );

        when(transacaoService.obterRecentes())
            .thenReturn(transacoes);

        Estatistica estatisticas = estatisticaService.obterEstatisticas();
        assertEquals(new Estatistica(2, 30, 15, 10, 20), estatisticas);
    }
}
