package com.mauroraya.transacao_api.requests;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

public record CriarTransacaoRequest(
    @PositiveOrZero(message = "O valor deve ser maior ou igual a 0")
    BigDecimal valor,

    @NotNull(message = "Data e hora são obrigatórias")
    @PastOrPresent(message = "Data e hora não podem estar no futuro")
    OffsetDateTime dataHora
) {}
