package com.mauroraya.transacao_api.models;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record Transacao(
    BigDecimal valor,
    OffsetDateTime dataHora
) {}
