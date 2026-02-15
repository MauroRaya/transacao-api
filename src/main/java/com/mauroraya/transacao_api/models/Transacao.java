package com.mauroraya.transacao_api.models;

import java.time.OffsetDateTime;

public record Transacao(
    double valor,
    OffsetDateTime dataHora
) {}
