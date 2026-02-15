package com.mauroraya.transacao_api.models;

public record Estatistica(
    long count,
    double sum,
    double avg,
    double min,
    double max
) {}
