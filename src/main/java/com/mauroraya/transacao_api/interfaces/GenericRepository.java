package com.mauroraya.transacao_api.interfaces;

public interface GenericRepository<T> {
    void criar(T item);
}
