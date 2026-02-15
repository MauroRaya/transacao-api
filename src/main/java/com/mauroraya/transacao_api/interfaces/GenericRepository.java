package com.mauroraya.transacao_api.interfaces;

import java.util.List;

public interface GenericRepository<T> {
    List<T> obter();
    void criar(T item);
    void deletarTodos();
}
