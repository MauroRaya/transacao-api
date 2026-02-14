package com.mauroraya.transacao_api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.mauroraya.transacao_api.models.Transacao;
import com.mauroraya.transacao_api.requests.CriarTransacaoRequest;
import com.mauroraya.transacao_api.services.TransacaoService;

import jakarta.validation.Valid;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("transacao")
public class TransacaoController {
    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<?> criarTransacao(
        @RequestBody @Valid CriarTransacaoRequest request,
        BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity
                .unprocessableContent()
                .build();
        }

        Transacao transacao = new Transacao(
            request.valor(),
            request.dataHora()
        );

        transacaoService.criar(transacao);

        URI location = URI.create(String.format(
            "/transacao/%s",
            transacao.dataHora()));

        return ResponseEntity
            .created(location)
            .build();
    }
}
