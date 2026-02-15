package com.mauroraya.transacao_api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<?> obterEstatisticas(
        @RequestParam(required = false, defaultValue = "60") int intervalo
    ) {
        Estatistica estatisticas = estatisticaService.obterEstatisticas(intervalo);
        return ResponseEntity.ok(estatisticas);
    }
}
