package com.mauroraya.transacao_api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mauroraya.transacao_api.models.Estatistica;
import com.mauroraya.transacao_api.services.EstatisticaService;

@RestController
@RequestMapping("estatistica")
public class EstatisticaController {
    private final EstatisticaService estatisticaService;

    public EstatisticaController(EstatisticaService estatisticaService) {
        this.estatisticaService = estatisticaService;
    }

    @GetMapping
    public Estatistica obterEstatisticas() {
        return estatisticaService.obterEstatisticas();
    }
}
